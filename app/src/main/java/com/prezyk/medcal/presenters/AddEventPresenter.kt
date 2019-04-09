package com.prezyk.medcal.presenters

class AddEventPresenter(view: View) {

    fun onStartDateTextViewClick() {}

    fun onEndDateTextViewClick() {}

    fun onSubmitButtonClick() {}

    public interface View {
        fun setStartDate()
        fun setEndDate()
    }
}