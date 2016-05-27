package com.example.samsung.suggestionsysteminfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    private String[] museumNames = {"İzmir Arkeoloji Ve Etnoğrafya Müzesi", "Neşe Ve Karikatür Müzesi", "Ümran Baradan Oyuncak Müzesi",
            "İzmir Tarih Ve Sanat Müzesi", "Ege Üniversitesi Etnografya Müzesi", "Atatürk Müzesi", "İzmir Mask Müzesi", "Tabiat Tarihi Müzesi",
            "Yeşilova Höyüğü Neolitik Yerleşimi Müzesi", "Bornova Kent Arşivi Ve Müzesi", "İzmir Ticaret Tarihi Müzesi", "Arkas Denizcilik Müzesi",
            "Zübeyde Hanım Deniz Müzesi", "Ege Üniversitesi Kağıt ve Kitap Sanatları Müzesi", "Konak Belediyesi Radyo ve Demokrasi Müzesi",
            "Ahmet Piriştina Kent Arşivi Ve Müzesi", "Karşıyaka Bahçeşehir Bilim Müzesi"};

    private String[] museumIDs = {"52594ad311d277825d470348", "4f168729e4b0005031187944", "4e9e887e55037bf933085627",
            "4d494a404509721e537722b0", "4ee71b49d3e34ebcf24c7b13", "5507dfda498eed8c510ee267", "50eab794e4b07c15c99de325",
            "4ea15311e3008f7d9196a100", "5329885c498e05ac74a8473b", "5131d431e4b082687be15de1", "53638b47498e5f20daa9dcba",
            "4fbdfd8be4b0e75751ba6bf4", "53625dba11d2b40c5f2b095e", "52591e4f498e016c4199f64a", "529c553011d27d422383d76a",
            "4d1b2ff111fca0939e0c9cce", "4f02da6f722e0f0774ceb20c"};

    private String[] exhibitionNames = {"İzmir Resim Heykel Müzesi ve Galerisi", "Selçuk Yaşar Resim Müzesi Ve Sanat Galerisi",
            "Hamza Rüstem Fotoğraf Evi", "Folkart Gallery","Resim Heykel Müzesi Kültürpark Sanat Galerisi"};

    private String[] exhibitionIDs = {"4e1b10f57d8bb17ec63a696b", "507d4ef1e4b0f2e0889c6f98", "4ea037c329c201f5d073bebb",
            "54d5c90d498ef8d0f5bc4517","50421780e4b0d3bcedaabfaf"};

    private String[] fairNames = {"Fuar Alanı Hol 1-A", "Fuar Alanı Hol 1-B", "Fuar Alanı Hol 2", "Fuar Alanı Hol 3",
            "Fuar Alanı Hol 4 ID", "Fuar Alanı Hol 5", "Fuar Çim Konser Alanı", "Fuar Lunaparkı",
            "İBB Fuar Sanat Sokağı","İsmet İnönü Sanat Merkezi", "İzmir Kültürpark Fuar Alanı Food Court","Fuar Gençlik Tiyatrosu",
            "Fuar İzmir - Travel Turkey","Foodex İzmir","Yapı Fuarı","Jewex Izmir Kuyumculuk Fuari-D Holu",
            "SHOEXPO Ayakkabı Ve Çanta Fuarı","If Wedding Fashion","MODEKO İzmir Furniture Fair", "Travel Turkey",
            "Agroexpo İzmir Tarım Fuarı","MARBLE 2016 Uluslararası Doğaltaş ve Teknolojileri Fuarı",
            "FUAR İZMİR INELEX 2016 Asansör Ve Asansör Teknolojileri Fuari","103A","Yem-Miks","Smyrna Sokak İzmir Fuar",
            "Batı Ege Mermer A-152 İzmir Fuar","Hall B Fuar izmir","Eskimo Mobilya Fuar","NETMER HALL B 118",
            "Fuar İzmir A Holl Yasa Taş","Fuar İzmir B Holl My Granit","Cekicler Marble Hall B","METAS",
            "Shoe Expo 2016","Sezgin Marble ( Hall B no. 165 )","Sina Mobilya İzmir Fuar Alanı","Asgüreller Mermer A109",
            "Fair İzmir - TurkeyBuild'15 İZMİR","Hall B Fuar İzmir B137 Kremna Marble","HATKO KAUÇUK STANDI",
            "Le Papillion by MODECA","Platinum Stones"};

    private String[] fairIDs = {"4f64df21e4b05c1d59c17e76", "501fce25e4b01ea6a343f750", "512f2b9be4b07792ce57f5cd",
            "545b4620498e223c53e5c08b", "504b9e33e4b09a44318df5d8", "5152eb9be4b0507a3e760c50", "52222fb411d2da59a6748c53",
            "4e68f558c65be3a5652bffb9","55e1cfa7498e0f592decd008","4b9bab18f964a520701836e3","54dde336498ef8f9abb93206",
            "51502e12e4b0be820e19b4a9","5669202c498edb1cc8e669c1","563b16b8cd109bb13f3bd1b7","563af804498eaf512ab3445c",
            "56b33d1b498efde4876f7dff","56d713d4498e3da9798f89ec","56af6f5a498e8dd1ce6d3612","56cc248fcd10a414a45b6c80",
            "5669202c498edb1cc8e669c1","56b9dcba498e3a987f70580f","56e31ac0498efb2e1d06aa5f","5706091f498e2594805ac951",
            "56c03d1bcd10bbbbae1cac95","56c04726cd1020ede0ef59f2","56d31d1ecd10004231c85037","56f289a6498e4d47d6e5f21f",
            "56bb47e3498ef3fe8aa3b2f6","56cd8cfb498e8978c319c596","55166af8498e48cd449e019f","56efb1d0498e0692c25fd638",
            "56f3a7f3498e5bfa69d0435f","56f2acc2498ea02faddd2ca6","56c0598dcd100614a8a1c59f","56dc89f6498e4a7229e23990",
            "56ee744f498e3b34bc97c835","56d03be4498e4199973b9c9a","56f28637498ebd311625e7b4","563daabccd108b8d8ff1c0d9",
            "56f3f30fcd104c93b4508cff","56bdf1df498e51112774860a","56b1ab6b498e8704e344dcb1","56f249b3498eac8f5f5a4829",
            ""}; 

    public HashMap<String, List<Venue>> getData() {
        HashMap<String, List<Venue>> expandableListDetail = new HashMap<>();

        List<Venue> museums = new ArrayList<>();
        for (int i = 0; i < museumNames.length; i++) {
            museums.add(new Venue(museumNames[i], museumIDs[i]));
        }

        List<Venue> exhibitions = new ArrayList<>();
        for (int i = 0; i < exhibitionNames.length; i++) {
            exhibitions.add(new Venue(exhibitionNames[i], exhibitionIDs[i]));
        }

        List<Venue> fair = new ArrayList<>();
        for (int i = 0; i < fairNames.length; i++) {
            fair.add(new Venue(fairNames[i], fairIDs[i]));
        }

        expandableListDetail.put("Exhibitions", exhibitions);
        expandableListDetail.put("Museums", museums);
        expandableListDetail.put("Fair", fair);
        return expandableListDetail;
    }
}
