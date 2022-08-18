package uz.isystem.dictionary2.core.dialog

import android.R
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import uz.isystem.dictionary2.core.database.DataBase
import uz.isystem.dictionary2.databinding.AddDialogBinding

class AddDialog(context: Context) : Dialog(context) {

    private var _binding: AddDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = AddDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSaveDictionary()

        window!!.setBackgroundDrawableResource(R.color.transparent)
    }

    fun onSaveDictionary() {
        var eng: String
        var uzb: String

        binding.addButton.setOnClickListener(View.OnClickListener {
            eng = binding.editTextEng.text.toString()
            uzb = binding.editTextUzb.text.toString()

            val english: String = eng
            val uzbek: String = uzb
            if (!english.isEmpty() && !uzbek.isEmpty()) {
                DataBase.getDataBase().addDictionary(eng, uzb)
                dismiss()
            } else {
            }

        })

        binding.cancelButton.setOnClickListener(View.OnClickListener { dismiss() })

    }
}

