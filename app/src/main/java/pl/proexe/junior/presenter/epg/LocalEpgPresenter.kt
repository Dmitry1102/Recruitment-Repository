package pl.proexe.junior.presenter.epg

import pl.proexe.junior.model.data.DayTile
import pl.proexe.junior.model.data.TvProgramme
import pl.proexe.junior.model.data.TvProgrammeCategory
import pl.proexe.junior.model.repository.LocalEpgRepository
import pl.proexe.junior.model.repository.TimeRepository
import pl.proexe.junior.view.epg.EpgView

class LocalEpgPresenter : EpgPresenter {

    private lateinit var view: EpgView
    private val epgRepository = LocalEpgRepository()
    private val timeRepository = TimeRepository()

    override fun onViewCreated(view: EpgView) {
        this.view = view
        refreshDays()
        refreshProgrammes()
        provideNavigationDrawer()
        getCategories()
    }

    private fun refreshDays() =
        view.showDaysList(
            timeRepository.getDayTiles()
        )

    private fun refreshProgrammes() =
        view.showEpgList(
            getProgrammes()
        )

    override fun onEpgProgrammeClick(programme: TvProgramme) {
        //No-op
    }

    override fun onDayTileClick(dayTile: DayTile) =
        view.selectDayTile(dayTile)

    override fun onCategoryClick(category: TvProgrammeCategory) =
          view.showEpgList(
            getProgrammes().filter {
                category == TvProgrammeCategory.ALL || category == it.category
            }
        )

    private fun getCategories(){
       view.showCategories(epgRepository.provideCategories())
    }

    private fun provideNavigationDrawer(){
        view.showNavigationDrawer(epgRepository.createNavigationDrawerModule())
    }


    private fun getProgrammes() =
        epgRepository.getProgrammesForDateTime(timeRepository.getCurrentTime())
}