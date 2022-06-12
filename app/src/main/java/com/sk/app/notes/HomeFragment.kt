package com.sk.app.notes

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sk.app.notes.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
lateinit var binding:FragmentHomeBinding
val viewModel: NotesViewModel by viewModels()
    var oldMyNotes= arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        viewModel.getNotes().observe(viewLifecycleOwner,{notesList->
            oldMyNotes = notesList as ArrayList<Notes>
            val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
            binding.recAllNotes.layoutManager = staggeredGridLayoutManager
            adapter = NotesAdapter(requireContext(),notesList)
            binding.recAllNotes.adapter = adapter

        })

        binding.filterall.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes = notesList as ArrayList<Notes>

                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.recAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.recAllNotes.adapter = adapter

            })
        }
        binding.filterHigh.setOnClickListener{
            viewModel.getHighNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes = notesList as ArrayList<Notes>

                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.recAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.recAllNotes.adapter = adapter

            })
        }
        binding.filterMedium.setOnClickListener{
            viewModel.getMediumNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes = notesList as ArrayList<Notes>

                val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL)
                binding.recAllNotes.layoutManager = staggeredGridLayoutManager
                adapter = NotesAdapter(requireContext(),notesList)
                binding.recAllNotes.adapter = adapter

            })
        }
        binding.filterLow.setOnClickListener{
            viewModel.getLowNotes().observe(viewLifecycleOwner,{notesList->
                oldMyNotes = notesList as ArrayList<Notes>

                binding.recAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                adapter = NotesAdapter(requireContext(),notesList)
                binding.recAllNotes.adapter = adapter

            })
        }

        binding.addNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_createNotesFragment2)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        val item = menu.findItem(R.id.search_item)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?) {
        val newFilteredList  = arrayListOf<Notes>()
for (i in oldMyNotes){
    if (i.title.contains(p0!!) || i.subTitle.contains(p0!!) || i.notes.contains(p0!!)){
        newFilteredList.add(i)
    }
}
        adapter.filtering(newFilteredList)
    }


}