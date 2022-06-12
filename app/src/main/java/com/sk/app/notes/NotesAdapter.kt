package com.sk.app.notes


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sk.app.notes.databinding.NotesItemBinding


class NotesAdapter(val requireContext: Context, var notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {
    class notesViewHolder(val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
fun filtering(newFilteredList: ArrayList<Notes>) {
    notesList = newFilteredList
    notifyDataSetChanged()
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
      return notesViewHolder(NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
      val data = notesList[position]
        holder.binding.itemTitle.text = data.title
        holder.binding.itemSubtitle.text = data.subTitle
        holder.binding.itemDate.text = data.date
        when(data.priority){
            "1"->{
                holder.binding.itemPriority.setBackgroundResource(R.drawable.green_proty)
            }
            "2"->{
                holder.binding.itemPriority.setBackgroundResource(R.drawable.yellow_proty)

            }
            "3"->{
                holder.binding.itemPriority.setBackgroundResource(R.drawable.red_proty)

            }

        }
        holder.binding.root.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragment2ToEditNotesFragment2(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount()= notesList.size


}