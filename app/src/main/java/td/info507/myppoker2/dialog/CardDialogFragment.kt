package td.info507.myppoker2.dialog

import CardStorage
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import td.info507.myppoker2.R
import td.info507.myppoker2.activity.Updatable
import td.info507.myppoker2.model.Card

class CardDialogFragment(private val updatable: Updatable): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_card, null)
        return AlertDialog.Builder(context)
        .setTitle(R.string.dialog_creation)
        .setView(view)
        .setPositiveButton(R.string.dialog_confirm) {_, _ ->
            CardStorage.get(requireContext()).insert(
                Card (
                    0,
                    view.findViewById<EditText>(R.id.card_value).text.toString(),
                    view.findViewById<EditText>(R.id.card_description).text.toString(),
                    view.findViewById<EditText>(R.id.card_color).text.toString(),
                )
            )
            updatable.update()
        }
        .setNegativeButton(R.string.dialog_cancel, null)
        .create()
    }
}