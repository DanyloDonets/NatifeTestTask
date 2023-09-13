package donets.danylo.natife.testtask.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import donets.danylo.natife.testtask.Data.DataObject
import donets.danylo.natife.testtask.R
import donets.danylo.natife.testtask.activities.MainActivity

class GifsAdapter(val context: Context, val gifs: MutableList<DataObject>):RecyclerView.Adapter<GifsAdapter.ViewHolder>() {

    lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItenClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.gif_item,parent,false), mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = gifs[position]

        Glide.with(context).load(data.images.ogImage.url).into(holder.imageView)
    }



    override fun getItemCount(): Int {
        return gifs.size
    }


    class ViewHolder(itemView: View, listener: OnItemClickListener): RecyclerView.ViewHolder(itemView){
        val imageView = itemView.findViewById<ImageView>(R.id.gif)

        init{
            itemView.setOnClickListener {
                listener.onItenClick(adapterPosition)

            }
        }

    }

}