package com.sk.app.notes

import android.os.Bundle
import android.text.format.DateFormat
import android.text.format.DateFormat.format
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sk.app.notes.databinding.FragmentCreateNotesBinding
import java.util.*

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.prgreen.setImageResource(R.drawable.done)

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
        binding.saveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.createTitle.text
        val subtitle = binding.createSubtitle.text
        val notes = binding.createDesc.text
        val d = Date()
        val notesDates: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)
        val data = Notes(
            null,
            title = title.toString(),
            subTitle = subtitle.toString(),
            notes = notes.toString(),
            date = notesDates.toString(),
            priority
        )
        viewModel.addNotes(data)
        findNavController().popBackStack()
        Toast.makeText(context, "Notes Created Successfully", Toast.LENGTH_SHORT).show()
    }

}