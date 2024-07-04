package umc.hackathon.presentation.treasure



import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import umc.hackathon.R
import umc.hackathon.databinding.ItemLayoutBinding

class ItemAdapter(): RecyclerView.Adapter<Holder>() {
    var dataList = mutableListOf<Item>() //이 어댑터에서 사용할 데이터 목록 변수를 선언하기 -> 빈 리스트는 항상 생성과 동시에 타입 명시하기

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder { //layout 생성해서 Holder에게 전달해주기
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) { //화면에 보여주는 함수
        val data = dataList[position] //position값이 파라미터이므로 사용 가능
        holder.setText(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

//여기서도 리사이클러뷰 상속받기, 뷰홀더를 통해서 자원을 효율적으로 운용할 수 있다.
class Holder(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){ //어댑터가 생성한 바인딩 레이아웃을 Holder에서 받아서 부모 클래스에 전달한다.

    fun setText(data: Item){ //어댑터에서 제공받은 레이아웃과 Main에서 인스턴스화 시킬 때 제공받는 데이터를 여기서 합친다.
        if(data.lock == true){
            Glide.with(itemView).load(R.drawable.bronze).into(binding.itemAlbumCoverImgIv)
        }else{
            Glide.with(itemView).load(R.drawable.gold).into(binding.itemAlbumCoverImgIv)
        }
        binding.textView.text = data.place
        binding.textView2.text = "D-${data.D_day}"

    }
}


data class Item(val lock: Boolean, val place: String, val people: Int, val D_day: Int)
