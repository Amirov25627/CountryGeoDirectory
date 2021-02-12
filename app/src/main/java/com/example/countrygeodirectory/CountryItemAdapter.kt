package com.example.countrygeodirectory

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countrygeodirectory.databinding.CountryItemBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class CountryItemAdapter(private val viewModel: SharedViewModel) : RecyclerView.Adapter<CountryViewHolder>() {
    val mList = mutableListOf<CountryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(inflater, parent, false)

        return CountryViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun setList(list: List<CountryData>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }
}


class CountryViewHolder(private val binding: CountryItemBinding, private val viewModel: SharedViewModel): RecyclerView.ViewHolder(binding.root){
    fun bind(data: CountryData){
        binding.countryName.text = data.name
        binding.countryCapital.text = data.capital

        val uri = Uri.parse("https://restcountries.eu/data/${data.alpha3Code.toLowerCase()}.svg")
       GlideToVectorYou
               .init()
               .with(binding.root.context)
               //.setPlaceHolder(placeholderLoading, placeholderError)
               .load(uri, binding.flagIcon)

        GlideToVectorYou
                .init()
                .with(binding.root.context)
                //.setPlaceHolder(placeholderLoading, placeholderError)
                .load(uri, binding.backFlag)

        binding.root.setOnClickListener{
            viewModel.goToDetails(data)
        }
    }


}