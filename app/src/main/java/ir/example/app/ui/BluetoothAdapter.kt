package ir.example.app.ui

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BluetoothAdapter(
    var bluetoothDevices:List<BluetoothDevice>,
    val clickListener: (BluetoothDevice) -> Unit
) : RecyclerView.Adapter<BluetoothAdapter.BluetoothVH>() {

    class BluetoothVH(item: View) : RecyclerView.ViewHolder(item) {
        val textView = itemView.findViewById<TextView>(android.R.id.text1)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BluetoothVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return BluetoothVH(view)
    }

    override fun getItemCount(): Int = bluetoothDevices.count()

    override fun onBindViewHolder(holder: BluetoothVH, position: Int) {
        holder.textView.text = bluetoothDevices[position].name
        holder.textView.setOnClickListener{
            clickListener.invoke(bluetoothDevices[position])
        }
    }
}

