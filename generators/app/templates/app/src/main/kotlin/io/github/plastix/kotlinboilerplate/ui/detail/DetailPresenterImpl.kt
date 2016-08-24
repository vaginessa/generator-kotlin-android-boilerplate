package <%= appPackage %>.ui.detail

import <%= appPackage %>.ui.base.AbstractPresenter
import javax.inject.Inject

class DetailPresenterImpl @Inject constructor() : AbstractPresenter<DetailView>(), DetailPresenter {
}