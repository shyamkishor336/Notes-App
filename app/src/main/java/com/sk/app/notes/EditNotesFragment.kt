package com.sk.app.notes

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sk.app.notes.databinding.FragmentEditNotesBinding
import java.util.*
import java.util.zip.Inflater

class EditNotesFragment : Fragment() {
    val oldNotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    val viewModel: NotesViewModel by viewModels()
    var priority: String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
setHasOptionsMenu(true)
        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubtitle.setText(oldNotes.data.subTitle)
        binding.editDesc.setText(oldNotes.data.notes)

        when (oldNotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.prgreen.setImageResource(R.drawable.done)
                binding.pred.setImageResource(0)
                binding.pryellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pryellow.setImageResource(R.drawable.done)
                binding.pred.setImageResource(0)
                binding.prgreen.setImageResource(0)

            }
            "3" -> {
                priority = ""
                binding.pred.setImageResource(R.drawable.done)
                binding.prgreen.setImageResource(0)
                binding.pryellow.setImageResource(0)

            }

        }
        binding.prgreen.setOnClickListener {
            priority = "1"
            binding.prgreen.setImageResource(R.drawable.done)
            binding.pryellow.setImageResource(0)
            binding.pred.setImageResource(0)
        }
        binding.pryellow.setOnClickListener {
            priority = "2"
            binding.pryellow.setImageResource(R.drawable.done)
            binding.prgreen.setImageResource(0)
            binding.pred.setImageResource(0)
        }
        binding.pred.setOnClickListener {
            priority = "3"
            binding.pred.setImageResource(R.drawable.done)
            binding.prgreen.setImageResource(0)
            binding.pryellow.setImageResource(0)
        }
        binding.updateNotes.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text
        val subtitle = binding.editSubtitle.text
        val notes = binding.editDesc.text
        val d = Date()
        val notesDates: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        val data = Notes(
            oldNotes.data.id,
            title = title.toString(),
            subTitle = subtitle.toString(),
            notes = notes.toString(),
            date = notesDates.toString(),
            priority
        )
        viewModel.updateNotes(data)
        findNavController().popBackStack()
        Toast.makeText(context, "Notes updated Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.delete_item){
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.delete_dialog)
            val delteYes = bottomSheet.findViewById<TextView>(R.id.deleteYes)
            val delteNo = bottomSheet.findViewById<TextView>(R.id.deleteNo)

            delteYes?.setOnClickListener{
                viewModel.deleteNotes(oldNotes.data.id!!)
                findNavController().popBackStack()
                bottomSheet.dismissWithAnimation
            }
            delteNo?.setOnClickListener {
                bottomSheet.dismissWithAnimation
            }
            bottomSheet.show()
        }
        return super.onOptionsItemSelected(item)
    }

}