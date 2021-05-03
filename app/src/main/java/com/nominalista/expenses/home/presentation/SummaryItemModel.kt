package com.nominalista.expenses.home.presentation

import android.content.Context
import com.nominalista.expenses.R
import com.nominalista.expenses.data.model.Currency
import com.nominalista.expenses.home.presentation.DateRange.*

class SummaryItemModel(
    context: Context,
    val currencySummaries: List<Pair<Currency, Double>>,
    val dateRange: DateRange
) : HomeItemModel {

    var itemModels: List<CurrencySummaryItemModel>
    var dateRangeText: String
    var dateRangeChange: ((DateRange) -> Unit)? = null

    init {
        itemModels = createItemModels(currencySummaries)
        dateRangeText = createDateRangeText(context, dateRange)
    }

    private fun createItemModels(currencySummaries: List<Pair<Currency, Double>>) =
            currencySummaries.map { createCurrencySummaryItemModel(it.first, it.second) }

    private fun createCurrencySummaryItemModel(currency: Currency, amount: Double) =
            CurrencySummaryItemModel(currency, amount)

    private fun createDateRangeText(context: Context, dateRange: DateRange): String {
        return when (dateRange) {
            TODAY -> context.getString(R.string.today)
            THIS_WEEK -> context.getString(R.string.this_week)
            THIS_MONTH -> context.getString(R.string.this_month)
            THIS_YEAR -> context.getString(R.string.this_year)
            LAST_MONTH -> context.getString(R.string.last_month)
            LAST_3_MONTHS -> context.getString(R.string.last_three_months)
            LAST_YEAR -> context.getString(R.string.last_year)
            ALL_TIME -> context.getString(R.string.all_time)
            CUSTOM -> context.getString(R.string.custom_date)
        }
    }

    fun onTodayClick() = dateRangeChange?.invoke(TODAY)

    fun onThisWeekClick() = dateRangeChange?.invoke(THIS_WEEK)

    fun onThisMonthClick() = dateRangeChange?.invoke(THIS_MONTH)

    fun onThisYearClick() = dateRangeChange?.invoke(THIS_YEAR)

    fun onLastMonthClick() = dateRangeChange?.invoke(LAST_MONTH)

    fun onLastThreeMonthsClick() = dateRangeChange?.invoke(LAST_3_MONTHS)

    fun onLastYearClick() = dateRangeChange?.invoke(LAST_YEAR)

    fun onAllTimeClick() = dateRangeChange?.invoke(ALL_TIME)

    fun onCustomDateClick() = dateRangeChange?.invoke(ALL_TIME)
}