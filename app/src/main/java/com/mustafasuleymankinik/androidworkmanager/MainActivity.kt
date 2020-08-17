package com.mustafasuleymankinik.androidworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

const val A_INPUT_DATA="A-inputdata"
const val C_INPUT_DATA="C-inputdata"
class MainActivity : AppCompatActivity() {

    lateinit var workManager: WorkManager
    lateinit var constraints: Constraints
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inputDataA = Data.Builder().putString(A_INPUT_DATA, "A").build()
        val inputDataC=Data.Builder().putString(C_INPUT_DATA,"C").build()
        constraints = Constraints.Builder().setRequiresCharging(true).build()
        workManager = WorkManager.getInstance(this)


        val oneTimeWorkRequestA= OneTimeWorkRequestBuilder<AWorker>().setInitialDelay(1,TimeUnit.SECONDS).setInputData(inputDataA).build()
        val oneTimeWorkRequestB= OneTimeWorkRequestBuilder<BWorker>().setInitialDelay(1,TimeUnit.SECONDS).build()
        val oneTimeWorkRequestC= OneTimeWorkRequestBuilder<CWorker>().setInitialDelay(1,TimeUnit.SECONDS).setInputData(inputDataC).build()
        val oneTimeWorkRequestD= OneTimeWorkRequestBuilder<DWorker>().setInitialDelay(1,TimeUnit.SECONDS).build()
        val oneTimeWorkRequestE= OneTimeWorkRequestBuilder<EWorker>().setInitialDelay(1,TimeUnit.SECONDS).build()

        applyButton.setOnClickListener(View.OnClickListener {
            workManager
                .beginWith(listOf(oneTimeWorkRequestA,oneTimeWorkRequestC))
                .then(listOf(oneTimeWorkRequestB,oneTimeWorkRequestD))
                .then(oneTimeWorkRequestE).enqueue()
        })
        cancelButton.setOnClickListener(View.OnClickListener {
            workManager.cancelAllWork()
        })

        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestA.id).observe(this, Observer {

            textView.append("State A: ${it.state} Output A: ${it.outputData} \n")
        })
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestB.id).observe(this, Observer {

            textView.append("State B: ${it.state} Output B: ${it.outputData} \n")
        })
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestC.id).observe(this, Observer {

            textView.append("State C: ${it.state} Output C: ${it.outputData} \n")
        })
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestD.id).observe(this, Observer {

            textView.append("State D: ${it.state} Output D: ${it.outputData} \n")
        })
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequestE.id).observe(this, Observer {

            textView.append("State E: ${it.state} Output E: ${it.outputData} \n")
        })
    }
}
