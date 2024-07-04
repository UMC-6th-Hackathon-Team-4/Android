package umc.hackathon.presentation.treasure



import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import umc.hackathon.R
import umc.hackathon.databinding.ItemLayoutBinding

class ItemAdapter(private val context: Context, private var dataList : List<Item>): RecyclerView.Adapter<Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding, context)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = dataList[position]
        holder.setText(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

//여기서도 리사이클러뷰 상속받기, 뷰홀더를 통해서 자원을 효율적으로 운용할 수 있다.
class Holder(val binding: ItemLayoutBinding, val context:Context): RecyclerView.ViewHolder(binding.root){ //어댑터가 생성한 바인딩 레이아웃을 Holder에서 받아서 부모 클래스에 전달한다.

    fun setText(data: Item){ //어댑터에서 제공받은 레이아웃과 Main에서 인스턴스화 시킬 때 제공받는 데이터를 여기서 합친다.
        if(data.lock == true){
            Glide.with(itemView).load(R.drawable.bronze).into(binding.itemAlbumCoverImgIv)
        }else{
            Glide.with(itemView).load(R.drawable.gold).into(binding.itemAlbumCoverImgIv)
        }
        binding.textView.text = data.place
        binding.textView2.text = "D-${data.D_day}"

        binding.itemAlbumCoverImgIv.setOnClickListener {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_preview_treasure, null)

            val dialogBuilder = AlertDialog.Builder(context).setView(dialogView).setPositiveButton("ok"){dialog,_->
                dialog.dismiss()
            }
                .setNegativeButton("Cancel"){dialog,_->
                    dialog.dismiss()
                }

            val dialogPlace = dialogView.findViewById<TextView>(R.id.place_tv)
            val dialogSpeak = dialogView.findViewById<TextView>(R.id.speak_tv)
            val dialogLock = dialogView.findViewById<TextView>(R.id.lock_tv)
            val dialogLock2 = dialogView.findViewById<TextView>(R.id.main)
            val dialogpeople = dialogView.findViewById<TextView>(R.id.people_tv)
            val dialogImg = dialogView.findViewById<ImageView>(R.id.sample_image)

            dialogPlace.text = data.place
            dialogSpeak.text = data.speak
            if(data.lock==true){
                dialogLock.text = "완료"
                dialogLock2.text = "보물 추가하기"
            }else{
                dialogLock.text = "${data.D_day}일 남음"
                dialogLock2.text = "보물 보러가기"
            }
            dialogpeople.text = "${data.people}명의 모임"
            Glide.with(itemView).load(data.Img).into(dialogImg)

            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }
}


data class Item(val lock: Boolean, val place: String, val people: Int, val D_day: Int, val speak: String, val Img: Int)
