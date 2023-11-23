package tv.brid.app_compose.ui.common.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    private val _sharedFlow = MutableSharedFlow<NavigationAction>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun navigateTo(navTarget: NavigationAction) {
        _sharedFlow.tryEmit(navTarget)
    }

    fun goBack() {
        _sharedFlow.tryEmit(NavigationAction.GoBack)
    }
}