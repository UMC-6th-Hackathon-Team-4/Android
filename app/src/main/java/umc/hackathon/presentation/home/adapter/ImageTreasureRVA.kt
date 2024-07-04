package umc.hackathon.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import umc.hackathon.R
import umc.hackathon.databinding.ItemTreasureBinding

//class ImageTreasureRVA (private val treasures : List<TreasureData>) : RecyclerView.Adapter<ImageTreasureRVA.TreasureViewHolder>() {
//
//    data class TreasureData(val imageResId: Int, val description: String)
//
//    //ViewHolder 클래스
//    inner class TreasureViewHolder(treasureview: View) : RecyclerView.ViewHolder(treasureview){
//        val treasureView: ImageView = treasureview.findViewById(R.id.treasureView)
//        val textView: TextView = treasureview.findViewById(R.id.textView)
//    }


    class ImageTreasureRVA(
        private val treasures: List<TreasureData>,
        private val onItemClick: (TreasureData) -> Unit
    ) : RecyclerView.Adapter<ImageTreasureRVA.TreasureViewHolder>() {

        data class TreasureData(val imageResId: Int, val description: String)

        inner class TreasureViewHolder(treasureView: View) : RecyclerView.ViewHolder(treasureView) {
            val treasureView: ImageView = treasureView.findViewById(R.id.treasureView)
            val textView: TextView = treasureView.findViewById(R.id.textView)

            init {
                treasureView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClick(treasures[position])
                    }
                }
            }
        }


    // onCreateViewHolder: 뷰 홀더를 생성하여 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreasureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_treasure, parent, false)
        return TreasureViewHolder(view)
    }
    // onBindViewHolder: 뷰 홀더에 데이터를 바인딩
    override fun onBindViewHolder(holder: TreasureViewHolder, position: Int) {
        val item = treasures[position]
        holder.treasureView.setImageResource(item.imageResId)
        holder.textView.text = item.description
    }

    override fun getItemCount(): Int = treasures.size
}