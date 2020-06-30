package com.sourceit.whiteteam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sourceit.whiteteam.adapter.WeatherAdapter
import com.sourceit.whiteteam.network.ApiService
import com.sourceit.whiteteam.network.RequestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_all_days_weather.*

class AllDaysWeather : Fragment() {

    private lateinit var disposable: Disposable
    private lateinit var weatherAdapter: WeatherAdapter
    private var listOfWeather: MutableList<RequestModel> = ArrayList()


    companion object {
        fun newInstance() = AllDaysWeather()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_days_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherAdapter = WeatherAdapter()

        cocktails_list.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        disposable = ApiService.getFiveDayWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showInfo(it)
            }, {
                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            })
    }

    private fun showInfo(weatherInfo: RequestModel) {
        weatherAdapter.update(weatherInfo.list)
    }
}