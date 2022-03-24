package com.bignerdranch.andoid.criminalintent

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.andoid.criminalintent.Crime.Companion.A_TYPE
import com.bignerdranch.andoid.criminalintent.Crime.Companion.B_TYPE
import com.bignerdranch.andoid.criminalintent.viewModels.CrimeListViewModel
import java.text.DateFormat
import java.util.*

private const val TAG = "CrimeListFragment"
class CrimeListFragment: Fragment() {

    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var crimeRecyclerView: RecyclerView

    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

    //private var repository: CrimeRepository? = null
    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
    //    CrimeListViewModelFactory((requireActivity().application as CriminalIntentApplication).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  Log.d(TAG, "Total cimes: ${crimeListViewModel.crimes.size}")
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        crimeRecyclerView.adapter = adapter

     //   updateUI()
//        val crime = Crime(B_TYPE, "test2test",Date(), false)
//        crimeListViewModel.insert(crime)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(viewLifecycleOwner) {crimes ->
            crimes?.let {
                Log.i(TAG, "Got crimes ${crimes.size}")
                updateUI(crimes)
            }
        }
        /*crimeListViewModel.CrimeListLiveData.observe(viewLifecycleOwner) {
                crimes ->
                    crimes?.let {
                        Log.i(TAG, "Got crimes ${crimes.size}")
                        updateUI(crimes)
                    }
        }*/
    }

    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter

    }

    private inner class CrimeHolder(view: View)
        : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var crime: Crime
        val titleTextView: TextView = view.findViewById(R.id.crime_item_title)
        val dateTextView: TextView = view.findViewById(R.id.crime_date)
        private val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        fun bind(crime: Crime) {
            this.crime = crime
            val format1: DateFormat = DateFormat.getDateInstance(DateFormat.FULL)
            val format2 = DateFormat.getDateInstance(DateFormat.LONG)
            val format3 = DateFormat.getDateInstance(DateFormat.LONG)
            val format4 = DateFormat.getDateInstance(DateFormat.SHORT)

            titleTextView.text = this.crime.title
            val date = format1.format(this.crime.date)
            dateTextView.text = date.toString()
            solvedImageView.visibility = if (crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
           // Toast.makeText(context, "${crime.title} pressed", Toast.LENGTH_SHORT).show()
            callbacks?.onCrimeSelected(crime.id)
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>)
        : RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            val view2 = layoutInflater.inflate(R.layout.list_item_crime, parent, false)

            return when(viewType) {
                Crime.A_TYPE -> {
                    return CrimeHolder(view)
                }
                Crime.B_TYPE -> {
                    return  CrimeHolder(view2)
                }
                else  -> throw RuntimeException("알 수 없는 뷰 타입 에러")
            }
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
           /* holder.apply {
                titleTextView.text = crime.title
                dateTextView.text = crime.date.toString()
            }*/
            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

        override fun getItemViewType(position: Int): Int {
            return crimes[position].type
        }
    }

    companion object {
        fun newInstance(): CrimeListFragment {
            return CrimeListFragment()
        }
    }


}
