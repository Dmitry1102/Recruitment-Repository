package pl.proexe.junior.view.epg

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import pl.proexe.junior.R
import pl.proexe.junior.adapter.DatePickAdapter
import pl.proexe.junior.adapter.OnItemClickListener
import pl.proexe.junior.adapter.ProgramAdapter
import pl.proexe.junior.databinding.ActivityMainBinding
import pl.proexe.junior.model.data.*
import pl.proexe.junior.model.repository.LocalEpgRepository
import pl.proexe.junior.presenter.epg.EpgPresenter
import pl.proexe.junior.presenter.epg.LocalEpgPresenter
import java.util.*
import kotlin.collections.ArrayList

class EpgActivity : AppCompatActivity(), EpgView, OnItemClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val presenter: EpgPresenter = LocalEpgPresenter()
    private val repository: LocalEpgRepository = LocalEpgRepository()
    private val programAdapter = ProgramAdapter()
    private var programmes: List<TvProgramme> = listOf()
    private var drawerModel: NavigationDrawerModel? = null
    private var listCategories = listOf<TvProgrammeCategory>()

    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onViewCreated(this)

        binding.ivTypes.setOnClickListener {
            makeDialog()
        }

        transferData()

    }

    override fun showEpgList(programmes: List<TvProgramme>) {
        this.programmes = programmes
        programAdapter.submitList(programmes)
        binding.rvList.adapter = programAdapter
    }

    override fun showDaysList(days: List<DayTile>) {
        val dateAdapter = DatePickAdapter(this)
        dateAdapter.submitList(days)
        binding.rvDate.adapter = dateAdapter
    }

    override fun showCategories(categories: List<TvProgrammeCategory>) {
        listCategories = categories

    }

    override fun showNavigationDrawer(drawerModel: NavigationDrawerModel) {
        this.drawerModel = drawerModel
    }

    override fun selectDayTile(dayTile: DayTile) {
        TODO("Not yet implemnted")
    }


    @SuppressLint("RtlHardcoded")
    private fun makeDialog() {
        val builder = AlertDialog.Builder(this)
        val layout =
            LayoutInflater.from(this).inflate(R.layout.tool_fragment, null)
        builder.setView(layout)

        //not sure what I'm using enum correctly , please describe in more detail
        //what enum is ultimately used for

        val textViewAll = layout.findViewById<TextView>(R.id.tv_all)
        val textViewFavourite =layout.findViewById<TextView>(R.id.tv_favourite)
        val textViewKids = layout.findViewById<TextView>(R.id.tv_kids)
        val textViewMusic = layout.findViewById<TextView>(R.id.tv_music)
        val textViewSport = layout.findViewById<TextView>(R.id.tv_sport)
        val textViewLifestyle = layout.findViewById<TextView>(R.id.tv_lifestyle)
        val textViewGeneral = layout.findViewById<TextView>(R.id.tv_general)
        val textViewEducation = layout.findViewById<TextView>(R.id.tv_education)
        val textViewInfo = layout.findViewById<TextView>(R.id.tv_information)
        val textViewMovies = layout.findViewById<TextView>(R.id.tv_films)

        textViewAll.text = listCategories[0].type
        textViewFavourite.text = listCategories[1].type
        textViewKids.text = listCategories[2].type
        textViewEducation.text = listCategories[3].type
        textViewMovies.text = listCategories[4].type
        textViewInfo.text = listCategories[5].type
        textViewMusic.text = listCategories[6].type
        textViewGeneral.text = listCategories[7].type
        textViewSport.text = listCategories[8].type
        textViewLifestyle.text = listCategories[9].type

        val dialog = builder.create()
        val attributes = dialog.window?.attributes
        attributes?.gravity = Gravity.TOP or Gravity.RIGHT
        dialog.show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        binding.rvDate.adapter = null
        binding.rvList.adapter = null
    }

    @SuppressLint("SimpleDateFormat", "NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(dayTile: DayTile) {
        val date = Date(dayTile.timestamp)
        val list = repository.getProgrammesForDateTime(date)
        programAdapter.submitList(list)
        programAdapter.notifyDataSetChanged()
        binding.rvList.adapter = programAdapter
    }

    private fun transferData() {
        val fragment = EpgFragment()
        val bundle = Bundle()
        bundle.putString(IMAGE, drawerModel?.logoUrl)
        bundle.putString(INFO_PROFILE, drawerModel?.accountInfo?.userName)
        bundle.putLong(DATA, drawerModel?.accountInfo!!.userAccountValue)
        fragment.arguments = bundle

        binding.ivMainFrame.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.mainActivity.id, fragment)
                .commit()
        }

    }

    companion object {
        const val IMAGE = "IMAGE"
        const val INFO_PROFILE = "INFO"
        const val DATA = "DATA"
    }


}
