package com.crimpsonlogic.demo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.krishna.adapters.MoviesListAdapter
import com.example.krishna.model.MoviesApiService
import com.example.krishna.model.Response
import com.example.krishna.model.SearchItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback


class MainActivity : AppCompatActivity() {
    private val apiSerivce = MoviesApiService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.length!! >= 3) {
                    callMoviesListAPI(p0.toString())
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0)
                }
            }

        })
    }

    fun callMoviesListAPI(filter: String?) {
        setDatavisibiity(1)
        val call: Call<Response> = apiSerivce.movieList(1,filter)
        call.enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                //Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                setDatavisibiity(2)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
//                Toast.makeText(
//                    this@MainActivity,
//                    "SUCCESS.... " + response.body()?.search.toString(),
//                    Toast.LENGTH_SHORT
//                ).show()
                setDatavisibiity(3)
                setAdapter(response.body())
            }

        })
    }

    private fun setAdapter(body: Response?) {
        movieList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MoviesListAdapter(
                this@MainActivity,
                body?.search as MutableList<SearchItem>
            )
        }
    }

    fun apiFailureofEmptyResponse() {
        setDatavisibiity(2)
    }

    fun apiSuccesResponse() {
        setDatavisibiity(3)
    }

    fun setDatavisibiity(pos: Int) {

        when (pos) {
            1 -> {
                movieList.visibility = View.GONE
                noData.visibility = View.GONE
                loadingProgressbar.visibility = View.VISIBLE
            }
            2 -> {
                movieList.visibility = View.GONE
                noData.visibility = View.VISIBLE
                loadingProgressbar.visibility = View.GONE
            }
            3 -> {
                movieList.visibility = View.VISIBLE
                noData.visibility = View.GONE
                loadingProgressbar.visibility = View.GONE
            }
        }
    }
}

