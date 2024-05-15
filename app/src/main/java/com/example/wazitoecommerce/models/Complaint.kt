package com.example.wazitoecommerce.models

class Complaint {
    var complainant:String = ""
    var complaintdes:String = ""
    var phoneno:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(complainant: String, complaintdes: String, phoneno: String, imageUrl: String, id: String) {
        this.complainant = complainant
        this.complaintdes = complaintdes
        this.phoneno = phoneno
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}