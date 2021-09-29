import android.content.Context
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.template.Data
import com.example.template.R

class Adapter3(
    private val gold: List<Data>,
    val listener: OnItemClickListener,
    val context: Context,
) : RecyclerView.Adapter<Adapter3.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageButton = view.findViewById<ImageButton>(R.id.imageButton)
        val userIcon = view.findViewById<ImageView>(R.id.userIcon)



        init {
           imageButton.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            if (v == imageButton) {
                Log.i("TAG", "image")
                listener.onItemClick(adapterPosition, gold, userIcon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return gold.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.userIcon.setImageResource(gold[position].icon)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, list: List<Data>, view: View)
    }
}


