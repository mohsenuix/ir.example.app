package ir.example.app.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber


class SimpleScannerActivity : AppCompatActivity() ,ZXingScannerView.ResultHandler{
    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this) // Programmatically initialize the scanner view
        setContentView(mScannerView) // Set the scanner view as the content view
    }

    override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera() // Start camera on resume
    }

    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera() // Stop camera on pause
    }

    override fun handleResult(rawResult: Result?) {
        //        Log.v(FragmentActivity.TAG, rawResult.getText()) // Prints scan results
//        Log.v(
//            FragmentActivity.TAG,
//            rawResult.getBarcodeFormat().toString()
//        ) // Prints the scan format (qrcode, pdf417 etc.)
        // If you would like to resume scanning, call this method below:
        Timber.i("barcode result is : ${rawResult?.text}")

        mScannerView!!.resumeCameraPreview(this)
    }
}