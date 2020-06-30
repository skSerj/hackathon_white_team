package com.sourceit.whiteteam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sourceit.whiteteam.adapter.WeatherAdapter
import com.sourceit.whiteteam.network.ApiService
import com.sourceit.whiteteam.network.RequestModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
        disposable = ApiService.getFiveDayWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listOfWeather.add(it)
                showInfo(it)
            }, {
                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            })
    }

    private fun showInfo(weatherInfo: RequestModel) {
        weatherAdapter.update(weatherInfo)
    }
}