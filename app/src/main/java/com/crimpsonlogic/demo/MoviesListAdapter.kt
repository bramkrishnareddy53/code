package com.example.krishna.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.krishna.R
import com.example.krishna.common.CircleTransform
import com.example.krishna.model.SearchItem
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movieitemview.view.*
import java.lang.Exception


class MoviesListAdapter(val mContext: Context, val moviesList: MutableList<SearchItem>) :
    RecyclerView.Adapter<MoviesListAdapter.ChildHolder>() {
    class ChildHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieName = view.movieName
        val yearTxt = view.yearTxt
        val imdbIdtxt = view.imdbIdtxt
        val movieTypetxt = view.movieTypetxt
        val moviePoster = view.moviePoster
        val progressBar =view.progressBar

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        val view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.movieitemview, parent, false)
        return ChildHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        val movieObj = moviesList[position] as SearchItem
        holder.movieName.text = movieObj.title
        holder.yearTxt.text = movieObj.year
        holder.movieTypetxt.text = movieObj.type
        holder.imdbIdtxt.text =movieObj.imdbID
        Picasso.get()
            .load(movieObj.poster)
            .centerCrop()
            .transform(CircleTransform(50,0))
            .fit()
            .into(holder.moviePoster,object :Callback {
                override fun onSuccess() {
                    holder.progressBar.setVisibility(View.GONE)
                }

                override fun onError(e: Exception?) {

                }})

}
}