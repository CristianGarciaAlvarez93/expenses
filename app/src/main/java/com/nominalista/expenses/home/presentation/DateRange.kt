package com.nominalista.expenses.home.presentation

import com.nominalista.expenses.util.extensions.*
import org.threeten.bp.LocalDate

enum class DateRange {

    TODAY {
        override fun contains(date: LocalDate): Boolean {
            return LocalDate.now().isEqual(date)
        }
    },

    THIS_WEEK {
        override fun contains(date: LocalDate): Boolean {
            val now = LocalDate.now()
            val dayBeforeFirstDayOfWeek = now.firstDayOfWeek().yesterday()
            val dayAfterLastDayOfWeek = now.lastDayOfWeek().tomorrow()
            return date.isAfter(dayBeforeFirstDayOfWeek) && date.isBefore(dayAfterLastDayOfWeek)
        }
    },

    THIS_MONTH {
        override fun contains(date: LocalDate): Boolean {
            val now = LocalDate.now()
            val dayBeforeFirstDayOfMonth = now.firstDayOfMonth().yesterday()
            val dayAfterLastDayOfMonth = now.lastDayOfMonth().tomorrow()
            return date.isAfter(dayBeforeFirstDayOfMonth) && date.isBefore(dayAfterLastDayOfMonth)
        }
    },

    THIS_YEAR {
        override fun contains(date: LocalDate): Boolean {
            val now = LocalDate.now()
            val dayBeforeFirstDayOfPreviousYear = now.firstDayOfYear().yesterday()
            val dayAfterLastDayOfPreviousYear = now.lastDayOfYear().tomorrow()
            return date.isAfter(dayBeforeFirstDayOfPreviousYear) && date.isBefore(dayAfterLastDayOfPreviousYear)
        }
    },

    LAST_MONTH {
        override fun contains(date: LocalDate): Boolean {
            val now = LocalDate.now()
            val dayBeforeLastMonth = now.minusMonths(1).yesterday()
            val dayAfterToday = now.tomorrow()
            return date.isAfter(dayBeforeLastMonth) && date.isBefore(dayAfterToday)
        }
    },

    LAST_3_MONTHS {
        override fun contains(date: LocalDate): Boolean {
            val now = LocalDate.now()
            val dayBeforeLastThreeMonths = now.minusMonths(3).yesterday()
            val dayAfterToday = now.tomorrow()
            return date.isAfter(dayBeforeLastThreeMonths) && date.isBefore(dayAfterToday)
        }
    },

    LAST_YEAR {
        override fun contains(date: LocalDate): Boolean {
            val now = LocalDate.now()
            val dayBeforeFirstDayOfPreviousYear = now.minusYears(1).firstDayOfYear().yesterday()
            val dayAfterLastDayOfPreviousYear = now.minusYears(1).lastDayOfYear().tomorrow()
            return date.isAfter(dayBeforeFirstDayOfPreviousYear) && date.isBefore(dayAfterLastDayOfPreviousYear)
        }
    },

    ALL_TIME {
        override fun contains(date: LocalDate) = true
    },

    CUSTOM {
        override fun contains(date: LocalDate) = true
    };

    abstract fun contains(date: LocalDate): Boolean
}