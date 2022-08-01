package uz.coder.pirlsadmin

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import uz.coder.pirlsadmin.models.Pupil
import java.util.*


class MainActivity : ComponentActivity() {

    var users = 27
    var database = FirebaseDatabase.getInstance()
    var mDatabase = database.getReference("Pupil")

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = remember {
                mutableStateListOf<Pupil>()
            }
            val visble = remember {
                mutableStateOf(0.3f)
            }
            val color = remember {
                mutableStateOf(Color.Green)
            }
            val bgColor = remember {
                mutableStateOf(Color.Black)
            }
            val statusColor = remember {
                mutableStateOf(R.color.boldColor)
            }
            val statusColorBg = remember {
                mutableStateOf(R.color.title)
            }
            val textColor = remember {
                mutableStateOf(R.color.title_color)
            }
            val progressBar = remember {
                mutableStateOf(0f)
            }
            val barSize = remember {
                mutableStateOf(50.dp)
            }
            val ball1 = remember {
                mutableStateOf(0)
            }
            val ball2 = remember {
                mutableStateOf(0)
            }
            val ball3 = remember {
                mutableStateOf(0)
            }
            val ball4 = remember {
                mutableStateOf(0)
            }
            val sharifi = remember {
                mutableStateOf("")
            }

            val viloyati = remember {
                mutableStateOf("")
            }

            val tumani = remember {
                mutableStateOf("")
            }

            val maktabi = remember {
                mutableStateOf("")
            }

            val sinfi = remember {
                mutableStateOf("")
            }
            val fullName = remember {
                mutableStateOf("")
            }

            mDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    list.clear()
                    val children = p0.children
                    barSize.value = 0.dp
                    for (i in children) {
                        color.value = Color.Transparent
                        val value = i.getValue(Pupil::class.java)
                        list.add(value!!)
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    Toast.makeText(this@MainActivity, p0.message, Toast.LENGTH_SHORT).show()
                }
            })


            ConstraintLayout(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize()
            ) {
                val (rv, progress, prog) = createRefs()
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(100.dp)
                        .constrainAs(progress) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxSize(visble.value),
                    color = color.value,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(bgColor.value)
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(bgColor.value)
                    ) {
                        val (title, pro) = createRefs()
                        Text(
                            text = "PIRLS",
                            color = colorResource(id = textColor.value),
                            fontSize = MaterialTheme.typography.h2.fontSize,
                            modifier = Modifier
                                .constrainAs(title) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )

                        ProgressBar(
                            modifier = Modifier
                                .constrainAs(pro) {
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(title.bottom)
                                },
                            float = 10f, number = 50,
                            context = this@MainActivity,
                            statusColor = colorResource(id = statusColor.value),
                            statusColorBg = colorResource(id = statusColorBg.value),
                            progressBar = list.size.toFloat()
                        )


                    }
                }

//                ssssssssssssssssssssssssssss
                val sheetState = rememberBottomSheetState(
                    initialValue = BottomSheetValue.Collapsed
                )
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )
                val textBottomSheet = remember {
                    mutableStateOf("")
                }

                val scope = rememberCoroutineScope()
                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                text = fullName.value,
                                color = Color.Green,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            )
                            Row {
                                Text(
                                    text = "Birinchi ball :",
                                    color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = ball1.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                            Row {
                                Text(
                                    text = "Ikkinchi ball :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = ball2.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                            Row {
                                Text(
                                    text = "Uchinchi ball :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = ball3.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                            Row {
                                Text(
                                    text = "To'rtinchi ball :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = ball4.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
//                            sharifi
                            Row {
                                Text(
                                    text = "Sharifi :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = sharifi.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
//                            Viloyati
                            Row {
                                Text(
                                    text = "Viloyati :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = viloyati.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
//                            tumani
                            Row {
                                Text(
                                    text = "Tumani :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = tumani.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
//                            maktabi
                            Row {
                                Text(
                                    text = "Maktabi :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = maktabi.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
//                            sinfi
                            Row {
                                Text(
                                    text = "Sinfi :", color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = sinfi.value.toString(), color = Color.Green,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }
                    },
                    sheetBackgroundColor = Color.Black,
                    sheetPeekHeight = 0.dp
                ) {
                    ConstraintLayout(
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxSize()
                    ) {
                        val (bar, rv) = createRefs()
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(barSize.value)
                                .constrainAs(prog) {
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            color = Color.Green
                        )
                        rv(
                            list = list,
                            modifier = Modifier
                                .constrainAs(rv) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                },
                            selectPosition = {
                                fullName.value = "${list[it].ismi} ${list[it].familiyasi}"
                                ball1.value = list[it].ball1!!
                                ball2.value = list[it].ball2!!
                                ball3.value = list[it].ball3!!
                                ball4.value = list[it].ball4!!

                                sharifi.value = list[it].sharifi!!
                                viloyati.value = list[it].viloyat!!
                                tumani.value = list[it].tuman!!
                                maktabi.value = list[it].maktab_raqami?.toString()!!
                                sinfi.value = list[it].sinf.toString()
                                textBottomSheet.value = it.toString()
                                scope.launch {
                                    if (sheetState.isCollapsed) {
                                        sheetState.expand()
                                    } else {
                                        sheetState.collapse()
                                    }
                                }

                            }
                        )
                    }


                }
//                ssssssssssssssssssssssssss
            }


        }
    }


    @Composable
    fun ProgressBar(
        modifier: Modifier = Modifier,
        float: Float,
        number: Int,
        fontSize: TextUnit = 28.sp,
        radius: Dp = 50.dp,
        color: Color = colorResource(id = R.color.dark),
        strokeWidth: Dp = 3.dp,
        animDuration: Int = 1000,
        animDelay: Int = 0,
        context: Context,
        statusColor: Color,
        statusColorBg: Color,
        progressBar: Float

    ) {
        val status = remember {
            mutableStateOf(-270f)
        }
        object : CountDownTimer(100, 100) {
            override fun onTick(millisUntilFinished: Long) {
                if (status.value < 400f) {
                    status.value += 1f
                } else {
                    status.value += 0.001f
                }
            }

            override fun onFinish() {
                this.start()
            }
        }.start()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawLine(
                    color = statusColorBg,
                    Offset(-300f, 850f),
                    Offset(550f, 850f),
                    strokeWidth = Stroke.HairlineWidth + 4
                )
                drawLine(
                    color = statusColor,
                    Offset(-300f, 850f),
                    Offset(progressBar.toFloat(), 850f),
                    strokeWidth = Stroke.HairlineWidth + 10,
                )
            }
        }
    }


    @Composable
    fun rv(
        list: SnapshotStateList<Pupil>,
        modifier: Modifier,
        selectPosition: (Int) -> Unit
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(list.size) {
                Column(
                    modifier = Modifier
                        .border(0.1.dp, Color.DarkGray)
                        .background(Color.White)
                        .height(80.dp)
                        .padding(0.dp)
                        .clickable {
                            selectPosition(it)
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Color.Black
                            )
                            .clip(
                                RoundedCornerShape(0.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.2f)
                                .fillMaxHeight(0.9f)
                                .padding(5.dp)
                                .clip(RoundedCornerShape(5.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Green),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = list[it].ismi!![0].toString(),
                                    fontSize = MaterialTheme.typography.h3.fontSize,
                                    color = colorResource(id = R.color.dark)
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight()
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxHeight(0.6f)
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                text = list[it].ismi.toString(),
                                color = Color.White,
                                fontSize = MaterialTheme.typography.h5.fontSize
                            )
                            Text(
                                text = list[it].viloyat.toString(),
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 10.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize(1f),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                painter = painterResource(id = R.drawable.ic_down),
                                contentDescription = null
                            )
                        }
                    }
                }

            }

        }
    }
}