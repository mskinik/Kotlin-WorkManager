package com.mustafasuleymankinik.androidworkmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

const val A_OUTPUT_DATA="A-output"
class AWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {



        val inputDataString=inputData.getString(A_INPUT_DATA).toString()



        return Result.success(outputData(inputDataString))
    }


    private  fun outputData(inputDataString: String): Data
    {

        return Data.Builder().putString(A_OUTPUT_DATA, inputDataString).build()

    }
}