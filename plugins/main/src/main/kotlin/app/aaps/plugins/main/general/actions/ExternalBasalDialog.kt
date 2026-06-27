package app.aaps.plugins.main.general.actions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import app.aaps.plugins.main.R

class ExternalBasalDialog(private val onApplyCallback: (Double, LongInsulinType) -> Unit) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_external_basal, container, false)

        val editDose = view.findViewById<EditText>(R.id.edit_external_dose)
        val spinnerType = view.findViewById<Spinner>(R.id.spinner_insulin_type)
        val btnApply = view.findViewById<Button>(R.id.btn_apply_external)
        val btnCancel = view.findViewById<Button>(R.id.btn_cancel_external)

        val insulinTypes = LongInsulinType.values()
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            insulinTypes.map { it.displayName }
        )
        spinnerType.adapter = adapter
        spinnerType.setSelection(insulinTypes.indexOf(LongInsulinType.TOUJEO))

        btnApply.setOnClickListener {
            val doseStr = editDose.text.toString()
            if (doseStr.isNotEmpty()) {
                val dose = doseStr.toDoubleOrNull() ?: 0.0
                val selectedType = insulinTypes[spinnerType.selectedItemPosition]

                if (dose > 0) {
                    onApplyCallback(dose, selectedType)
                    dismiss()
                } else {
                    Toast.makeText(context, "Введите корректную дозу", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnCancel.setOnClickListener { dismiss() }
        return view
    }
}