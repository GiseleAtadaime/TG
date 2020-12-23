package com.trabalho.tg.View

import android.content.Context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.highsoft.highcharts.common.HIColor
import com.highsoft.highcharts.common.hichartsclasses.*
import com.highsoft.highcharts.core.HIChartView
import com.trabalho.tg.Controller.C_Entrada
import com.trabalho.tg.Helper.DBHelper
import com.trabalho.tg.Helper.cameraUtils
import com.trabalho.tg.Model.Lote
import com.trabalho.tg.Model.Lote_Fechado
import com.trabalho.tg.R
import kotlinx.android.synthetic.main.fragment_lote_alter_dialog.*
import kotlinx.android.synthetic.main.fragment_lote_detalhe.*
import java.io.File
import java.io.Serializable
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOTEPAM = "param1"
private const val LOTEPAMFECHADO = "loteFechado"
private const val USERID = "userid"
private const val AREAID = "areaid"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoteDetalheFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoteDetalheFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoteDetalheFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var lotePam: Lote? = null
    private var lotePamFechado: Lote_Fechado? = null
    private var userid: Int? = null
    private var areaId: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            lotePam = it.getSerializable(LOTEPAM) as Lote?
            lotePamFechado = it.getSerializable(LOTEPAMFECHADO) as Lote_Fechado?
            userid = it.getInt(USERID)
            areaId = it.getInt(AREAID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lote_detalhe, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onAlterButtonClick(lote : Lote,  tipo : Int,areaId :Int, userId : Int, loteId : Int) {
        listener?.onAlterButtonClick(lote, tipo, areaId, userId, loteId)
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
        fun onAlterButtonClick(lote : Lote, tipo : Int, areaId :Int, userId : Int, loteId : Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoteDetalheFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(lotePam: Lote?,lotePamFechado : Lote_Fechado?, user : Int, areaId : Int) =
            LoteDetalheFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(LOTEPAM, lotePam as Serializable?)
                    putSerializable(LOTEPAMFECHADO, lotePamFechado as Serializable?)
                    putInt(USERID, user)
                    putInt(AREAID, areaId)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(lotePam != null){
            txtNome_LoteDetFrag.text = lotePam!!.lot_nome
            txtPlanta_LoteDetFrag.text = lotePam!!.lot_planta

            fBtnAdd_LoteDetFrag.setOnClickListener{
                if (menu_LoteDetFrag.visibility == View.VISIBLE){
                    menu_LoteDetFrag.visibility = View.GONE

                }
                else{
                    menu_LoteDetFrag.visibility = View.VISIBLE

                }
            }
/*
        if(lotePam!!.lot_ent.size == 0){
            btnAlt_LoteDetFrag.visibility = View.GONE
        }*/

            imgBtnAlt_LoteDetFrag.setOnClickListener{
                onAlterButtonClick(lotePam!!,1, areaId!!,userid!!,lotePam!!.lot_id)
            }

            imgBtnExc_LoteDetFrag.setOnClickListener{
                onAlterButtonClick(lotePam!!,2, areaId!!,userid!!,lotePam!!.lot_id)
            }

            btnFech_LoteDetFrag.setOnClickListener{
                onAlterButtonClick(lotePam!!,3, areaId!!,userid!!,lotePam!!.lot_id)
            }

            btnAdd_LoteDetFrag.setOnClickListener{
                onAlterButtonClick(lotePam!!,4, areaId!!,userid!!,lotePam!!.lot_id)
            }

            btnAlt_LoteDetFrag.setOnClickListener{
                onAlterButtonClick(lotePam!!,5, areaId!!,userid!!,lotePam!!.lot_id)
            }


            var chartView : HIChartView =  view.findViewById(R.id.hc_lotepie)
            var options = HIOptions()
            var chart = HIChart()

            chart.type = "pie"
            chart.backgroundColor = null
            chart.plotBorderWidth = null
            chart.plotShadow = false
            options.chart = chart

            var title = HITitle()
            title.text = lotePam!!.lot_nome
            options.title = title


            var tooltip = HITooltip()
            tooltip.pointFormat = "{series.name}: <b>{point.percentage:.1f}%</b>"
            options.tooltip = tooltip

            var plotOptions = HIPlotOptions()
            plotOptions.pie = HIPie()
            plotOptions.pie.allowPointSelect = true
            plotOptions.pie.cursor = "pointer"
            plotOptions.pie.dataLabels = HIDataLabels()
            plotOptions.pie.dataLabels.enabled = false
            plotOptions.pie.showInLegend = true
            options.plotOptions = plotOptions



            val series1 = HIPie()
            series1.name = "Quantidade"
            var data = ArrayList<HashMap<String, Any>>()



            var tipo = C_Entrada().selectTipoDescricao(DBHelper(context))
            var i = 1
            var y = 0
            var z = 0
            for (t in tipo){
                var map1: HashMap<String, Any> = HashMap()
                map1.put("name", t)
                map1.put("y",  lotePam!!.totalTipoEntrada(i))

                data.add(y++,map1.clone() as HashMap<String, Any>)
                i++;
            }


            series1.data = data
            options.series = ArrayList(Arrays.asList(series1))
            chartView.options = options

            //Gráfico valores

            val chartView2: HIChartView = view.findViewById(R.id.hc_lotevalues)

            val options2 = HIOptions()

            val title2 = HITitle()
            title2.text = "Gastos totais"
            options2.title = title2

            val xAxis2 = HIXAxis()
            xAxis2.type = "category"
            xAxis2.labels = HILabels()
            xAxis2.labels.rotation = -45
            xAxis2.labels.style = HICSSObject()
            xAxis2.labels.style.fontSize = "13px"
            xAxis2.labels.style.fontFamily = "Verdana, sans-serif"
            options2.xAxis = object : ArrayList<HIXAxis?>() {
                init {
                    add(xAxis2)
                }
            }

            val yAxis2 = HIYAxis()
            yAxis2.min = 0
            yAxis2.title = HITitle()
            yAxis2.title.text = "Valor"
            options2.yAxis = object : ArrayList<HIYAxis?>() {
                init {
                    add(yAxis2)
                }
            }

            val legend2 = HILegend()
            legend2.enabled = false
            options2.legend = legend2

            val tooltip2 = HITooltip()
            tooltip2.pointFormat = "Valor total: <b>{point.y:.1f} Reais</b>"
            options2.tooltip = tooltip2

            val series2 = HIColumn()
            series2.name = "Atividades"
            var data2 = ArrayList<Any>()

            i = 1
            y = 0
            tipo.removeAt(tipo.lastIndex)
            for (t in tipo){
                var obj = arrayOf(t, lotePam!!.valorTotal(i))
                data2.add(y++, obj.clone())
                i++
            }


            series2.data = data2


            series2.dataLabels = HIDataLabels()
            series2.dataLabels.enabled = true
            series2.dataLabels.rotation = -90
            series2.dataLabels.color = HIColor.initWithHexValue("FFFFFF")
            series2.dataLabels.align = "right"
            series2.dataLabels.format = "{point.y:.1f}"
            series2.dataLabels.y = 10
            series2.dataLabels.style = HICSSObject()
            series2.dataLabels.style.fontSize = "13px"
            series2.dataLabels.style.fontFamily = "Verdana, sans-serif"
            options2.setSeries(ArrayList(asList(series2)))

            chartView2.options = options2

            if(lotePam!!.lot_imagem != null){
                val cw = ContextWrapper(context)
                val file = File(lotePam!!.lot_imagem)
                if (file.exists()) {
                    imgLoteDet.setImageBitmap(BitmapFactory.decodeFile(file.toString()))
                }
            }
        }
        else if(lotePamFechado != null){

            txtNome_LoteDetFrag.text = lotePamFechado!!.lot_nome
            txtPlanta_LoteDetFrag.text = lotePamFechado!!.lot_planta

            fBtnAdd_LoteDetFrag.setOnClickListener{
                if (menu_LoteDetFrag.visibility == View.VISIBLE){
                    menu_LoteDetFrag.visibility = View.GONE

                }
                else{
                    menu_LoteDetFrag.visibility = View.VISIBLE

                }
            }

            imgBtnAlt_LoteDetFrag.visibility = View.GONE
            imgBtnExc_LoteDetFrag.visibility = View.GONE
            btnFech_LoteDetFrag.visibility = View.GONE
            btnAdd_LoteDetFrag.visibility = View.GONE

            btnAlt_LoteDetFrag.setOnClickListener{
                onAlterButtonClick(Lote(0),5, areaId!!,userid!!,lotePamFechado!!.lot_id)
            }


            var chartView : HIChartView =  view.findViewById(R.id.hc_lotepie)
            var options = HIOptions()
            var chart = HIChart()

            chart.type = "pie"
            chart.backgroundColor = null
            chart.plotBorderWidth = null
            chart.plotShadow = false
            options.chart = chart

            var title = HITitle()
            title.text = lotePamFechado!!.lot_nome
            options.title = title


            var tooltip = HITooltip()
            tooltip.pointFormat = "{series.name}: <b>{point.percentage:.1f}%</b>"
            options.tooltip = tooltip

            var plotOptions = HIPlotOptions()
            plotOptions.pie = HIPie()
            plotOptions.pie.allowPointSelect = true
            plotOptions.pie.cursor = "pointer"
            plotOptions.pie.dataLabels = HIDataLabels()
            plotOptions.pie.dataLabels.enabled = false
            plotOptions.pie.showInLegend = true
            options.plotOptions = plotOptions



            val series1 = HIPie()
            series1.name = "Quantidade"
            var data = ArrayList<HashMap<String, Any>>()



            var tipo = C_Entrada().selectTipoDescricao(DBHelper(context))
            var i = 1
            var y = 0
            var z = 0
            for (t in tipo){
                var map1: HashMap<String, Any> = HashMap()
                map1.put("name", t)
                map1.put("y",  lotePamFechado!!.totalTipoEntrada(i))

                data.add(y++,map1.clone() as HashMap<String, Any>)
                i++;
            }


            series1.data = data
            options.series = ArrayList(Arrays.asList(series1))
            chartView.options = options

            //Gráfico valores

            val chartView2: HIChartView = view.findViewById(R.id.hc_lotevalues)

            val options2 = HIOptions()

            val title2 = HITitle()
            title2.text = "Gastos totais"
            options2.title = title2

            val xAxis2 = HIXAxis()
            xAxis2.type = "category"
            xAxis2.labels = HILabels()
            xAxis2.labels.rotation = -45
            xAxis2.labels.style = HICSSObject()
            xAxis2.labels.style.fontSize = "13px"
            xAxis2.labels.style.fontFamily = "Verdana, sans-serif"
            options2.xAxis = object : ArrayList<HIXAxis?>() {
                init {
                    add(xAxis2)
                }
            }

            val yAxis2 = HIYAxis()
            yAxis2.min = 0
            yAxis2.title = HITitle()
            yAxis2.title.text = "Valor"
            options2.yAxis = object : ArrayList<HIYAxis?>() {
                init {
                    add(yAxis2)
                }
            }

            val legend2 = HILegend()
            legend2.enabled = false
            options2.legend = legend2

            val tooltip2 = HITooltip()
            tooltip2.pointFormat = "Valor total: <b>{point.y:.1f} Reais</b>"
            options2.tooltip = tooltip2

            val series2 = HIColumn()
            series2.name = "Atividades"
            var data2 = ArrayList<Any>()

            i = 1
            y = 0
            tipo.removeAt(tipo.lastIndex)
            for (t in tipo){
                var obj = arrayOf(t, lotePamFechado!!.valorTotal(i))
                data2.add(y++, obj.clone())
                i++
            }


            series2.data = data2


            series2.dataLabels = HIDataLabels()
            series2.dataLabels.enabled = true
            series2.dataLabels.rotation = -90
            series2.dataLabels.color = HIColor.initWithHexValue("FFFFFF")
            series2.dataLabels.align = "right"
            series2.dataLabels.format = "{point.y:.1f}"
            series2.dataLabels.y = 10
            series2.dataLabels.style = HICSSObject()
            series2.dataLabels.style.fontSize = "13px"
            series2.dataLabels.style.fontFamily = "Verdana, sans-serif"
            options2.setSeries(ArrayList(asList(series2)))

            chartView2.options = options2

            if(lotePamFechado!!.lot_imagem != null){
                val cw = ContextWrapper(context)
                val file = File(lotePamFechado!!.lot_imagem)
                if (file.exists()) {
                    imgLoteDet.setImageBitmap(BitmapFactory.decodeFile(file.toString()))
                }
            }
        }
        else{
            //To do
        }



    }
}
