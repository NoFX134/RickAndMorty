package com.example.rickandmorty.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.Model.Character
import com.example.rickandmorty.Model.ResultsItem
import com.example.rickandmorty.R
import com.squareup.picasso.Picasso


class CharacterAdapter(val mItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {
    private var character = Character()
    private var characterList = character.results

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val name: TextView = itemView.findViewById(R.id.name)
        val gender: TextView = itemView.findViewById(R.id.gender)
        val species: TextView = itemView.findViewById(R.id.species)
        val cardView: CardView = itemView.findViewById(R.id.CardView)

        init {
            itemView.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return characterList?.size ?: -1
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(characterList?.get(position)?.image).into(holder.image)
        holder.name.text = characterList?.get(position)?.name.toString()
        holder.gender.text = characterList?.get(position)?.gender.toString()
        holder.species.text = characterList?.get(position)?.species.toString()


    }

    fun seData(newList: List<ResultsItem?>?) {
        characterList = newList
        notifyDataSetChanged()

    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

}



