package tv.brid.app_compose.ui.common

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    data class Success<out T>(val data: T) : ScreenState<T>()
    object Error : ScreenState<Nothing>()
}