package pl.proexe.junior.view.epg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.proexe.junior.adapter.DatePickAdapter
import pl.proexe.junior.adapter.ProgramAdapter
import pl.proexe.junior.databinding.ActivityMainBinding
import pl.proexe.junior.model.data.DayTile
import pl.proexe.junior.model.data.NavigationDrawerModel
import pl.proexe.junior.model.data.TvProgramme
import pl.proexe.junior.model.data.TvProgrammeCategory
import pl.proexe.junior.model.repository.TimeRepository
import pl.proexe.junior.presenter.epg.EpgPresenter
import pl.proexe.junior.presenter.epg.LocalEpgPresenter

class EpgActivity : AppCompatActivity(), EpgView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val presenter: EpgPresenter = LocalEpgPresenter()
    private val dateAdapter = DatePickAdapter()
    private val programAdapter = ProgramAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onViewCreated(this)
    }

    override fun showEpgList(programmes: List<TvProgramme>) {
        programAdapter.submitList(programmes)
        binding.rvList.adapter = programAdapter
    }

    override fun showDaysList(days: List<DayTile>) {
        dateAdapter.submitList(days)
        binding.rvDate.adapter = dateAdapter
    }

    override fun showCategories(categories: List<TvProgrammeCategory>) {

    }

    override fun showNavigationDrawer(drawerModel: NavigationDrawerModel) {

    }

    override fun selectDayTile(dayTile: DayTile) {

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        binding.rvDate.adapter = null
        binding.rvList.adapter = null
    }
}
