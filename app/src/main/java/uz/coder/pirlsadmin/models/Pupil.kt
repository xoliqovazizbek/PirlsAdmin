package uz.coder.pirlsadmin.models

class Pupil {

    var id: String? = null
    var ismi: String? = null
    var familiyasi: String? = null
    var sharifi: String? = null
    var viloyat: String? = null
    var tuman: String? = null
    var maktab_raqami: Int? = null
    var sinf: Int? = null
    var ball1: Int? = 0
    var ball2: Int? = 0
    var ball3: Int? = 0
    var ball4: Int? = 0

    constructor()

    constructor(
        id: String?,
        ismi: String?,
        familiyasi: String?,
        sharifi: String?,
        viloyat: String?,
        tuman: String?,
        maktab_raqami: Int?,
        sinf: Int?,
        ball1: Int?,
        ball2: Int?,
        ball3: Int?,
        ball4: Int?
    ) {
        this.id = id
        this.ismi = ismi
        this.familiyasi = familiyasi
        this.sharifi = sharifi
        this.viloyat = viloyat
        this.tuman = tuman
        this.maktab_raqami = maktab_raqami
        this.sinf = sinf
        this.ball1 = ball1
        this.ball2 = ball2
        this.ball3 = ball3
        this.ball4 = ball4
    }



}