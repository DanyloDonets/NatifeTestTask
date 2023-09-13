package donets.danylo.natife.testtask.AsyncLogic

import android.app.Application
import android.content.Context
import donets.danylo.natife.testtask.API.AppsFlyer.AppsFlyer
import donets.danylo.natife.testtask.API.OneSygnal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AsyncLogic : Application() {

    override fun onCreate() {
        super.onCreate()
        val context: Context = this
        GlobalScope.launch(Dispatchers.IO) {
            // Ініціалізація AppsFlyer
            AppsFlyer().init(context)

            // Ініціалізація OneSignal
            OneSignal(context).init()
        }
    }
}