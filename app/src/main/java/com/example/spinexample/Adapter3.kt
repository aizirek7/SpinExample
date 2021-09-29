import android.content.Context
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.spinexample.Data
import com.example.spinexample.R
import com.example.spinexample.databinding.ItemUserBinding

class Adapter3(
    private val gold: List<Data>,
    val listener: OnItemClickListener,
    val context: Context,
) : RecyclerView.Adapter<Adapter3.Holder>() {

    inner class Holder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.imageButton.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            if (v == binding.imageButton) {
                Log.i("TAG", "image")
                listener.onItemClick(adapterPosition, gold, binding.userIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context))
        val holder = Holder(binding)
        return holder
    }

    override fun getItemCount(): Int {
        return gold.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.userIcon.setImageResource(gold[position].icon)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, list: List<Data>, view: View)
    }
}


