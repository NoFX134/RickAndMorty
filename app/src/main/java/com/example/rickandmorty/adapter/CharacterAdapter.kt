package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Model.ResultsItem
import com.example.rickandmorty.R


class CharacterAdapter() : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {
    private var Character = Character()
    private var CharacterList = Character.results

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val species: TextView = itemView.findViewById(R.id.species)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return CharacterList?.size ?: -1
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = CharacterList?.get(position)?.name.toString()
        holder.gender.text = CharacterList?.get(position)?.gender.toString()
        holder.species.text = CharacterList?.get(position)?.species.toString()
    }

    fun seData(newList: List<ResultsItem?>?) {
        CharacterList = newList
        notifyDataSetChanged()

    }
}



