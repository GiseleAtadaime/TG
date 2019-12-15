package com.trabalho.tg.View

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(
        func: FragmentTransaction.() -> FragmentTransaction
) {
            beginTransaction().func().addToBackStack(null).commit()

}