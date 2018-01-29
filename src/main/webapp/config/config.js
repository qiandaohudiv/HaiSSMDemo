/**
 * Created by Admin on 2017/10/9.
 */
var baseUrl = 'http://localhost:8080';
var dataUrl = {
    login:{
        login:baseUrl+'/HaiSSMDemo/companyUserLogin.action',
        logout:baseUrl+'/HaiSSMDemo/logout.action',
    },
    company:{
        add:baseUrl+'/HaiSSMDemo/insertCompanyUserAndPass.action',
        getID:baseUrl+'/HaiSSMDemo/selectCompanyByName.action?current=1&pageNum=1000&name=',
        getName:baseUrl+'/HaiSSMDemo/selectCompanyByID.action?id=',
        getPeople:baseUrl+'/HaiSSMDemo/selectPeopleById.action',
    },
    alarm: {
        /*del: baseUrl + '/HaiSSMDemo/delPeopleBatch.action?arrays=',*/
        all: baseUrl + '/HaiSSMDemo/selectAllAlarm.action',
        getAlarmByCompanyID:baseUrl+'/HaiSSMDemo/selectAlarmByCompanyId.action',
    },
    goodsType: {
        del: baseUrl + '/HaiSSMDemo/delGoodsTypeBatch.action?arrays=',
        search: baseUrl + '/HaiSSMDemo/selectGoodsTypeByName.action?current=1&pageNum=1000&name=',
        all: baseUrl + '/HaiSSMDemo/allGoodsType.action',
        insert: baseUrl + '/HaiSSMDemo/insertGoodsType.action',
        upDate: baseUrl + '/HaiSSMDemo/updateGoodsType.action',
    },
    safeCard: {
        pdf: baseUrl + '/SafeCard/',
        search: baseUrl + '/HaiSSMDemo/selectBySafeCardName.action?current=1&pageNum=1000&safeCardName=',
        del: baseUrl + '/HaiSSMDemo/delSafeCardBatch.action?arrays=',
        uploadFile: baseUrl + '/HaiSSMDemo/upfiles.action',
        insert: baseUrl + '/HaiSSMDemo/insertSafeCard.action',
        upDate: baseUrl + '/HaiSSMDemo/updateSafeCard.action',
        all: baseUrl + '/HaiSSMDemo/allSafeCards.action',
    },
    carInfo: {
        getCarByCompanyName:baseUrl+'/HaiSSMDemo/selectCarInfoByCompanyName.action',
        allPeople: baseUrl + '/HaiSSMDemo/allPeople.action?current=1&pageNum=1000',
        search: baseUrl + '/HaiSSMDemo/selectCarByCarLicense.action?current=1&pageNum=1000&carLicense=',
        insert: baseUrl + '/HaiSSMDemo/insertCarInfo.action',
        upDate: baseUrl + '/HaiSSMDemo/updateCarInfo.action',
        del: baseUrl + '/HaiSSMDemo/delCarInfoBatch.action?arrays=',
        all: baseUrl + '/HaiSSMDemo/allCarInfo.action',
        map:baseUrl+'/HaiSSMDemo/selectGpsByPlateNO.action?plateNo=',
    },
    carrier: {
        search: baseUrl + '/HaiSSMDemo/selectCompanyByName.action?current=1&pageNum=1000&name=',
        insert: baseUrl + '/HaiSSMDemo/insertCompany.action',
        upDate: baseUrl + '/HaiSSMDemo/updateCompany.action',
        del: baseUrl + '/HaiSSMDemo/delCompanyBatch.action?arrays=',
        all: baseUrl + '/HaiSSMDemo/allCompany.action',
    },
    person: {
        search: baseUrl + '/HaiSSMDemo/selectPeopleByName.action?current=1&pageNum=1000&name=',
        del: baseUrl + '/HaiSSMDemo/delPeopleBatch.action?arrays=',
        upPicture: baseUrl + '/HaiSSMDemo/upPicture.action',
        all: baseUrl + '/HaiSSMDemo/allPeople.action?time=',
        insert: baseUrl + '/HaiSSMDemo/insertPeople.action',
        upDate: baseUrl + '/HaiSSMDemo/updatePeople.action',
        companyList: baseUrl + '/HaiSSMDemo/getCompanyList.action',
        upImg:baseUrl+'/HaiSSMDemo/upPicture.action',
    },
    goods: {
        goodsType: baseUrl + '/HaiSSMDemo/getGoodsTypeList.action',
        safeCard: baseUrl + '/HaiSSMDemo/getSafeCardsList.action',
        insert: baseUrl + '/HaiSSMDemo/insertGoodsInfo.action',
        upDate: baseUrl + '/HaiSSMDemo/updateGoodsInfo.action',
        search: baseUrl + '/HaiSSMDemo/selectGoodsInfoByName.action?current=1&pageNum=1000&name=',
        del: baseUrl + '/HaiSSMDemo/delGoodsInfoBatch.action?arrays=',
        all: baseUrl + '/HaiSSMDemo/allGoodsInfo.action',
    },
    waybill: {
        getWaybillByCompanyID:baseUrl+'/HaiSSMDemo/selWaybillByCompanyId.action',
        selectCheck: baseUrl + '/HaiSSMDemo/selectHlCheckWaybillBywaybillId.action?waybillId=',
        selectCheckText: baseUrl + '/HaiSSMDemo/selCheckTextArray.action?array=',
        selectDriverLog: baseUrl + '/HaiSSMDemo/selDriverLogTextArray.action?array=',
        goodInfo: baseUrl + '/HaiSSMDemo/selectGoodsInfoList.action',
        carInfo: baseUrl + '/HaiSSMDemo/getCarInfoList.action',
        search: baseUrl + '/HaiSSMDemo/selWaybillByShipper.action?current=1&pageNum=1000&Shipper=',
        insert: baseUrl + '/HaiSSMDemo/insertWaybill.action',
        upDate: baseUrl + '/HaiSSMDemo/updateWaybill.action',
        people: baseUrl + '/HaiSSMDemo/selectPeopleByName.action?current=1&pageNum=1&name=',
        del: baseUrl + '/HaiSSMDemo/delWaybillBatch.action?arrays=',
        all: baseUrl + '/HaiSSMDemo/allWaybill.action',
    },
    trainEdit: {
        del: baseUrl + '/HaiSSMDemo/delPeixu.action?array=',
        all: baseUrl + '/HaiSSMDemo/selectPeixu.action',
        insert: baseUrl + '/HaiSSMDemo/insertPeixu.action',
        search:baseUrl+'/HaiSSMDemo/selectPeixuByName.action?current=1&pageNum=1&name=',
    },
    trainLog: {
        all: baseUrl + '/HaiSSMDemo/selectPeixustate.action',
        getTrainLogByCompanyID:baseUrl+'/HaiSSMDemo/selectPeixunstateByCompanyId.action',
    }
}
