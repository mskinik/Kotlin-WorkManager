package com.mustafasuleymankinik.androidworkmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

const val E_OUTPUT_DATA="E-output"
class EWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val firstInputDataString=inputData.getString(B_OUTPUT_DATA).toString()
        val secondInputDataString=inputData.getString(D_OUTPUT_DATA).toString()

        return Result.success(outputData(firstInputDataString,secondInputDataString))
    }

    private fun outputData(firstInputDataString:String,secondInputDataString:String):Data
    {


        return Data.Builder().putString(E_OUTPUT_DATA,"$firstInputDataString-$secondInputDataString-E").build()
    }

}