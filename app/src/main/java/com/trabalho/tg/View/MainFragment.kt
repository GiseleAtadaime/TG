package com.trabalho.tg.View

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amazonaws.services.s3.model.S3DataSource
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
import com.trabalho.tg.Controller.C_Entrada
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.Utils_TG
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.Model.Usuario
import com.trabalho.tg.R
import java.util.ArrayList
import java.io.Serializable


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USUARIO = "usuario"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var usuario: Usuario? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            usuario = it.getSerializable(USUARIO) as Usuario
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(usuario : Usuario) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(USUARIO, usuario as Serializable)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var utils = Utils_TG()

            var chartView : HIChartView =  view.findViewById(R.id.hc_Main)
            var options = HIOptions()
            var chart = HIChart()

            chart.type = "bar"
            options.chart = chart

            var title = HITitle()
            title.text = "Resumo geral das atividades registradas"
            options.title = title


            var yaxis = HIYAxis()
            yaxis.min = 0
            yaxis.title = HITitle()
            yaxis.title.text = "Quantidade"
            yaxis.title.align = "high"
            yaxis.labels = HILabels()
            yaxis.labels.overflow = "justify"
            var arrayy = ArrayList<HIYAxis>()
            arrayy.add(yaxis)
            options.yAxis = arrayy

            var tooltip = HITooltip()
            tooltip.valueSuffix = ""
            options.tooltip = tooltip

            var plotOptions = HIPlotOptions()
            plotOptions.bar = HIBar()
            plotOptions.bar.dataLabels = HIDataLabels()
            plotOptions.bar.dataLabels.enabled = true
            options.plotOptions = plotOptions

            var legend = HILegend()
            legend.layout = "vertical"
            legend.align ="right"
            legend.verticalAlign = "top"
            legend.x = -40
            legend.y = 80
            legend.floating = true
            legend.borderWidth = 1
            legend.backgroundColor = HIColor.initWithHexValue("FFF")
            legend.shadow = true
            options.legend = legend

            var credits = HICredits()
            credits.enabled = false
            options.credits = credits
            var series = ArrayList<HISeries>()

            var lotes = ArrayList<Lote>()

            var xaxis = HIXAxis()
            var categories = ArrayList<String>()



            var tipo = C_Entrada().selectTipoDescricao(DBHelper(context))
            usuario!!.usr_area.forEach { var nome = it.ar_nome; it.ar_lote.forEach { lotes.add(it);
                lotes[lotes.lastIndex].lot_nome = lotes[lotes.lastIndex].lot_nome + "\n(" +  nome + ")" } }
            var i = 0
            var y = 0
            var z = 0
            for (t in tipo){
                if(!t.contentEquals("plantio") && !t.contentEquals("colheita")){
                    var bar = HIBar()
                    bar.name = t
                    var data = ArrayList<Int>()
                    for (lote in lotes){
                        categories.add(y++,lote.lot_nome)
                        data.add(i++,lote.lot_ent.count { it.ent_desc.contentEquals(t)})
                    }
                    bar.data = data.clone() as ArrayList<*>?
                    series.add(z++,bar)
                    i = 0
                }
            }


            xaxis.categories = categories
            var arrayx = ArrayList<HIXAxis>()
            arrayx.add(xaxis)
            options.xAxis = arrayx


            options.series = series
            chartView.options = options



    }
}
