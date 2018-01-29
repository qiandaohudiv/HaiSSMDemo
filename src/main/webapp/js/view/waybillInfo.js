/**
 * Created by Admin on 2017/9/26.
 */
new Vue({
    el: '#app',
    data: function () {
        return {
        	searchText:'',
            userType: '',
            totalRecord: 0, /*总页数*/
            page: {
                current: 1, /*当前页*/
                pageNum: 20/*每页数据量*/
            },
            formValidate: {
                shipper: '',
                consignDate: '',
                loadingDate: '',
                uploadDate: '',
                sendPlace: '',
                sendCity: '',
                loadPlace: '',
                loadLinkman: '',
                loadLinkmanPhone: '',
                receivePlace: '',
                receiveCity: '',
                uploadAdress: '',
                uploadLinkman: '',
                uploadLinkmanPhone: '',
                goodName: '',
                nationCode: '',
                code: '',
                total: '',
                weight: '',
                volume: '',
                freight: '',
                carNum: '',
                reserveOne: ''
            },
            ruleValidate: {
                shipper: [
                    {required: true, message: '托运方不能为空', trigger: 'blur'}
                ]
            },
            modal1: false,
            columns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center',
                    fixed: 'left'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '托运方',
                    key: 'shipper'
                },
                {
                    width: 120,
                    align: 'center',
                    title: '托运日期',
                    key: 'realConsignDate',
                    sortable: true
                },
                {
                    width: 120,
                    align: 'center',
                    title: '装货时间',
                    key: 'realLoadingDate',
                    sortable: true
                },
                {
                    width: 120,
                    align: 'center',
                    title: '卸货时间',
                    key: 'realUploadDate',
                    sortable: true
                },
                {
                    width: 100,
                    align: 'center',
                    title: '发货点',
                    key: 'sendPlace'
                },
                {
                    width: 150,
                    align: 'center',
                    title: '发货城市',
                    key: 'sendCity'
                },
                {
                    width: 150,
                    align: 'center',
                    title: '装货地址',
                    key: 'loadPlace'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '装货联系人',
                    key: 'loadLinkman'
                },
                {
                    width: 120,
                    align: 'center',
                    title: '装货联系人电话',
                    key: 'loadLinkmanPhone'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '收货点',
                    key: 'receivePlace'
                },
                {
                    width: 150,
                    align: 'center',
                    title: '收货城市',
                    key: 'receiveCity'
                },
                {
                    width: 150,
                    align: 'center',
                    title: '卸货地址',
                    key: 'uploadAdress'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '卸货联系人',
                    key: 'uploadLinkman'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '卸货联系人电话',
                    key: 'uploadLinkmanPhone'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '货物名称',
                    key: 'goodName'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '商品总数',
                    key: 'total',
                    sortable: true
                },
                {
                    width: 100,
                    align: 'center',
                    title: '重量',
                    key: 'weight',
                    sortable: true
                },
                {
                    width: 100,
                    align: 'center',
                    title: '体积',
                    key: 'volume',
                    sortable: true
                },
                {
                    width: 100,
                    align: 'center',
                    title: '运费',
                    key: 'freight',
                    sortable: true
                },
                {
                    width: 100,
                    align: 'center',
                    title: '车牌号',
                    key: 'carNum'
                },
                {
                    width: 100,
                    align: 'center',
                    title: '运单状态',
                    key: 'waybillState',
                    render: function (h, params) {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'text',
                                    size: 'small'
                                },
                                on: {
                                    click: function () {
                                        this.modal2 = true;
                                        var _self = this;
                                        this.theCarChecked = [];
                                        this.theDriverLog = [];
                                        $.ajax({
                                            type: 'GET',
                                            url: dataUrl.waybill.selectCheck+_self.data[params.index].id,
                                            cache: false,
                                            success: function (data) {
                                                _self.waybillMsg = data[0];
                                                /// 获得车辆检查数据
                                                if (_self.waybillMsg.checkcarArray) {
                                                    $.ajax({
                                                        type: 'GET',
                                                        url: dataUrl.waybill.selectCheckText,
                                                        cache: false,
                                                        data: _self.waybillMsg.checkcarArray,
                                                        success: function (data) {
                                                            _self.theCarChecked = data;
                                                        }
                                                    });
                                                }
                                                /// 获得行车日志
                                                if (_self.waybillMsg.driverlogArray) {
                                                    $.ajax({
                                                        type: 'GET',
                                                        url: dataUrl.waybill.selectDriverLog,
                                                        cache: false,
                                                        data: _self.waybillMsg.driverlogArray,
                                                        success: function (data) {
                                                            _self.theDriverLog = data;
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }.bind(this)
                                }
                            }, '查看')
                        ]);
                    }.bind(this)
                },
                {
                    width: 150,
                    title: '操作',
                    key: 'action',
                    align: 'center',
                    fixed: 'right',
                    render: function (h, params) {
                        if (this.userType === 1) {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: function () {
                                            this.openState = "修改";
                                            this.formValidate = JSON.parse(JSON.stringify(this.data[params.index]));
                                            // this.carList=[];
                                            // this.carList.push(this.formValidate);
                                            this.theSendCity = this.data[params.index].sendCity.split('/');
                                            this.theReceiveCity = this.data[params.index].receiveCity.split('/');
                                            if (!this.formValidate.goodName && this.goodsList.length > 0) {
                                                this.formValidate.goodName = this.goodsList[0].name;
                                                this.formValidate.nationCode = this.goodsList[0].code;
                                                this.formValidate.code = this.goodsList[0].reserveTwo;
                                            }
                                            if (!this.formValidate.carNum && this.carList.length > 0) {
                                                this.formValidate.carNum = this.carList[0].carNum;
                                                this.carMessage.allowWeight = this.carList[0].allowWeight;
                                                this.carMessage.guacarNum = this.carList[0].guacarNum;
                                                this.carMessage.carType = this.carList[0].carType;
                                                this.carMessage.driverName = this.carList[0].driverName;
                                                this.carMessage.safer = this.carList[0].safer;
                                            }
                                            if (this.carList[params.index]) {
                                                this.carPosition = params.index;
                                            }
                                            this.carChange(this.formValidate.carNum);
                                            this.modal1 = true;
                                        }.bind(this)
                                    }
                                }, '修改'),
                                h('Button', {
                                    props: {
                                        type: 'error',
                                        size: 'small'
                                    },
                                    on: {
                                        click: function () {
                                            this.delArr.push(this.data[params.index].id);
                                            this.del();
                                        }.bind(this)
                                    }
                                }, '删除')
                            ]);
                        } else {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'primary',
                                        size: 'small'
                                    },
                                    style: {
                                        marginRight: '5px'
                                    },
                                    on: {
                                        click: function () {
                                            this.openState = "查看";
                                            console.log(this.data[params.index]);
                                            this.formValidate = JSON.parse(JSON.stringify(this.data[params.index]));
                                            //this.carChange();
                                            this.modal1 = true;
                                        }.bind(this)
                                    }
                                }, '查看')
                            ]);
                        }

                    }.bind(this)
                }
            ],
            modal2: false,
            data: [],
            waybillMsg: {},
            cityData: [],
            theSendCity: [],
            theReceiveCity: [],
            openState: '',
            loading: false,
            file: null,
            delArr: [],
            goodsList: [],
            carList: [],
            goodsPosition: 0,
            carPosition: 0,
            carMessage: {
                driverPictureUrl: '',
                saferPictureUrl: ''
            },
            driverLog: [],
            carChecked: [],
            theDriverLog: [],
            theCarChecked: []
        }
    },
    created: function () {
        var _self = this;
        var textState = JSON.parse(Cookies.get("state"));
        if (textState != null) {
            if (textState.ID == 0) {
                window.location.href = "../../state.html";
            } else if (textState.ID == 1) {
                if (textState.roleID == 1) {
                    _self.userType = 1;
                } else if (textState.roleID == 2) {
                    _self.userType = 2;
                } else {
                    _self.userType = 3;
                }
                _self.getData();
            }
        } else {
            window.location.href = "../../state.html";
        }

        /// 获得货物名称
        $.ajax({
            type: 'GET',
            url: dataUrl.waybill.goodInfo,
            cache: false,
            success: function (data) {
                if (data.length > 0) {
                    _self.goodsList = data;
                    _self.formValidate.goodName = _self.goodsList[0].name;
                    _self.formValidate.nationCode = _self.goodsList[0].code;
                    _self.formValidate.code = _self.goodsList[0].reserveTwo;
                }
            }
        });

        /////获取车辆列表
        if (_self.userType === 3) {
            //根据公司Name查询数据
            _self.page.company_name = JSON.parse(Cookies.get("state")).companyName;
            $.ajax({
                type: 'GET',
                url: dataUrl.carInfo.getCarByCompanyName,
                data:_self.page,
                cache: false,
                success: function (data) {
                    if (data.dataList.length > 0) {
                        _self.carList = data.dataList;
                        _self.formValidate.carNum = data.dataList[0].carNum;
                        _self.carMessage.allowWeight = data.dataList[0].allowWeight;
                        _self.carMessage.guacarNum = data.dataList[0].guacarNum;
                        _self.carMessage.carType = data.dataList[0].carType;
                        _self.carMessage.driverName = data.dataList[0].driverName;
                        _self.carMessage.safer = data.dataList[0].safer;
                    }
                }
            });
        } else {
            $.ajax({
                type: 'GET',
                url: dataUrl.carInfo.all,
                data:_self.page,
                cache: false,
                success: function (data) {
                    if (data.dataList.length > 0) {
                        _self.carList = data.dataList;
                        _self.formValidate.carNum = data.dataList[0].carNum;
                        _self.carMessage.allowWeight = data.dataList[0].allowWeight;
                        _self.carMessage.guacarNum = data.dataList[0].guacarNum;
                        _self.carMessage.carType = data.dataList[0].carType;
                        _self.carMessage.driverName = data.dataList[0].driverName;
                        _self.carMessage.safer = data.dataList[0].safer;
                    }
                }
            });
        }

        /// 获得地区
        $.getJSON('../../cityData.json', function (data) {
            _self.cityData = data;
        });

    },
    mounted: function () {
        this.$refs.head.style.display = 'block';
    },
    methods: {
        search: function () {
            if (this.searchText.replace(/\s/g, '').length < 1) {
                alert('搜索内容不可为空');
            } else {
                var _self = this;
                $.ajax({
                    type: 'GET',
                    url: dataUrl.waybill.search+encodeURI(_self.searchText.replace(/\s/g, '')),
                    cache: false,
                    success: function (data) {
                        if (typeof data == "object") {
                            _self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            _self.data = data.dataList;
                        } else {
                            _self.data = [];
                        }
                    }
                });
            }
        },
        upMessage: function () {
            var _self = this;
            _self.loading = true;
            _self.$refs.formValidate.validate(function (valid) {
                if (_self.openState == "查看") {
                    _self.loading = false;
                    _self.modal1 = false;
                } else {
                    if (valid) {
                        // console.log(_self.formValidate);
                        _self.formValidate.sendCity = _self.theSendCity.join('/');
                        _self.formValidate.receiveCity = _self.theReceiveCity.join('/');
                        /////////
                        console.log(_self.formValidate);
                        if (_self.formValidate.consignDate instanceof Date) {
                            _self.formValidate.consignDate = _self.month(_self.formValidate.consignDate);
                        }
                        if (_self.formValidate.loadingDate instanceof Date) {
                            _self.formValidate.loadingDate = _self.month(_self.formValidate.loadingDate);
                        }
                        if (_self.formValidate.uploadDate instanceof Date) {
                            _self.formValidate.uploadDate = _self.month(_self.formValidate.uploadDate);
                        }
                        if (_self.openState == "添加") {
                            _self.formValidate.reserveOne = "未执行";
                            _self.formValidate.compayId = JSON.parse(Cookies.get("state")).companyID;
                            _self.postData(_self, dataUrl.waybill.insert, _self.formValidate);
                        }
                        else if (_self.openState == "修改") {
                            _self.postData(_self, dataUrl.waybill.upDate, _self.formValidate);
                        }
                    } else {
                        _self.loading = false;
                    }
                }
            });
        },
        month: function (date) {
            var m = '';
            if (date.getMonth() + 1 < 10) {
                m = "0" + (date.getMonth() + 1);
            } else {
                m = date.getMonth() + 1;
            }
            return date.getFullYear() + '-' + m + '-' + date.getDate();
        },
        time: function (times) {
            var d = new Date(parseInt(times));
            return d.getFullYear() + '/' + (d.getMonth() + 1) + '/' + d.getDate();
        },
        cancel: function () {
            this.modal1 = false;
        },
        carChange: function (num) {
        	if (num) {
        		// console.log(num);
        		for (var i = 0; i < this.carList.length; i++) {
        			if (this.carList[i].carNum === num) {
        				this.carPosition = i;
        				break;
        			} 
        		}
        	}
            this.carMessage.allowWeight = this.carList[this.carPosition].allowWeight;
            this.carMessage.guacarNum = this.carList[this.carPosition].guacarNum;
            this.carMessage.carType = this.carList[this.carPosition].carType;
            this.carMessage.driverName = this.carList[this.carPosition].driverName;
            this.carMessage.safer = this.carList[this.carPosition].safer;
            this.formValidate.carNum = this.carList[this.carPosition].carNum;
            var _self = this;
            // 驾驶员
            $.ajax({
                type: 'GET',
                url: dataUrl.waybill.people+encodeURI(_self.carList[_self.carPosition].driverName),
                cache: false,
                success: function (data) {
                    if (data.dataList.length > 0) {
                        _self.carMessage.driveLicence = data.dataList[0].driveLicence;
                        _self.carMessage.drivezigezheng = data.dataList[0].drivezigezheng;
                        _self.carMessage.driverPhone = data.dataList[0].phone;
                        _self.carMessage.driverPictureUrl = baseUrl + '/pic/' + data.dataList[0].pictureName;
                    }
                }
            });
            // 押运员
            $.ajax({
                type: 'GET',
                url: dataUrl.waybill.people+encodeURI(this.carList[this.carPosition].safer),
                cache: false,
                success: function (data) {
                    if (data.dataList.length > 0) {
                        _self.carMessage.saferzigezheng = data.dataList[0].drivezigezheng;
                        _self.carMessage.saferPhone = data.dataList[0].phone;
                        _self.carMessage.saferPictureUrl = baseUrl + '/pic/' + data.dataList[0].pictureName;
                    }
                }
            });
        },
        goodsChange: function () {
            this.formValidate.goodName = this.goodsList[this.goodsPosition].name;
            this.formValidate.code = this.goodsList[this.goodsPosition].reserveTwo;
            this.formValidate.nationCode = this.goodsList[this.goodsPosition].code;
        },
        del: function () {
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.waybill.del+_self.delArr,
                cache: false,
                success: function (data) {
                    _self.delArr = [];
                    _self.getData();
                    _self.$Message.info('刪除成功');
                }
            });
        },
        chooseAll: function (data) {
            var _self = this;
            for (var i in data) {
                _self.delArr.push(data[i].id);
            }
        },
        handleUpload1: function (file) {
            this.formValidate.safeCardFile = file;
            return false;
        },
        handleUpload2: function (file) {
            this.formValidate.introductionFile = file;
            return false;
        },
        next: function (data) {
            this.page.current = data;
            this.getData();
        },
        add0: function (m) {
            return m < 10 ? '0' + m : m
        },
        format: function (nS) {
            var time = new Date(parseInt(nS));
            var y = time.getFullYear();
            var m = time.getMonth() + 1;
            var d = time.getDate();
            return y + '-' + this.add0(m) + '-' + this.add0(d);
        },
        open: function (obj) {
            if ($.trim(obj.currentTarget.innerText) == "添加") {
                for (var key in this.formValidate) {
                    this.formValidate[key] = '';
                }
                // 初始化货物类型信息
                if (this.goodsList.length > 0) {
                    this.formValidate.goodName = this.goodsList[0].name;
                    this.formValidate.nationCode = this.goodsList[0].code;
                    this.formValidate.code = this.goodsList[0].reserveTwo;
                }
                // 初始化车辆类型信息
                if (this.carList.length > 0) {
                    this.formValidate.carNum = this.carList[0].carNum;
                    this.carMessage.allowWeight = this.carList[0].allowWeight;
                    this.carMessage.guacarNum = this.carList[0].guacarNum;
                    this.carMessage.carType = this.carList[0].carType;
                    this.carMessage.driverName = this.carList[0].driverName;
                    this.carMessage.safer = this.carList[0].safer;
                    this.carPosition = 0;
                    this.carChange();
                }
                this.openState = $.trim(obj.currentTarget.innerText);
            }
            //this.$refs['formValidate'].resetFields();
            this.modal1 = true;
        },
        getData: function () {
            var _self = this;
            _self.$Loading.start();
            if (_self.userType === 3) {
                //根据公司ID查询数据
                _self.page.company_id = JSON.parse(Cookies.get("state")).companyID;
                $.ajax({
                    type: 'GET',
                    url: dataUrl.waybill.getWaybillByCompanyID,
                    data:_self.page,
                    cache: false,
                    success: function (data) {
                        _self.$Loading.finish();
                        if (typeof data == "object") {
                            _self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            _self.data = data.dataList;
                            // 循环对时间进行处理
                            for (var i = 0; i < _self.data.length; i++) {
                                if (_self.data[i].consignDate) {
                                    _self.data[i].realConsignDate = _self.time(_self.data[i].consignDate);
                                    _self.data[i].consignDate = new Date(_self.data[i].consignDate);
                                }
                                if (_self.data[i].loadingDate) {
                                    _self.data[i].realLoadingDate = _self.time(_self.data[i].loadingDate);
                                    _self.data[i].loadingDate = new Date(_self.data[i].loadingDate);
                                }
                                if (_self.data[i].uploadDate) {
                                    _self.data[i].realUploadDate = _self.time(_self.data[i].uploadDate);
                                    _self.data[i].uploadDate = new Date(_self.data[i].uploadDate);
                                }
                            }
                        } else {
                            _self.data = [];
                        }
                    }
                });

            } else {
                $.ajax({
                    type: 'GET',
                    url: dataUrl.waybill.all,
                    data:_self.page,
                    cache: false,
                    success: function (data) {
                        _self.$Loading.finish();
                        if (typeof data == "object") {
                            _self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            _self.data = data.dataList;
                            // 循环对时间进行处理
                            for (var i = 0; i < _self.data.length; i++) {
                                if (_self.data[i].consignDate) {
                                    //console.log(_self.data[i]);
                                    _self.data[i].realConsignDate = _self.time(_self.data[i].consignDate);
                                    _self.data[i].consignDate = new Date(_self.data[i].consignDate);
                                }
                                if (_self.data[i].loadingDate) {
                                    _self.data[i].realLoadingDate = _self.time(_self.data[i].loadingDate);
                                    _self.data[i].loadingDate = new Date(_self.data[i].loadingDate);
                                }
                                if (_self.data[i].uploadDate) {
                                    _self.data[i].realUploadDate = _self.time(_self.data[i].uploadDate);
                                    _self.data[i].uploadDate = new Date(_self.data[i].uploadDate);
                                }
                            }
                        } else {
                            _self.data = [];
                        }
                    }
                });
            }
        },
        postData: function (_self, url, data) {
            $.ajax({
                type: 'POST',
                url: url,
                data:data,
                cache: false,
                success: function (data) {
                    _self.loading = false;
                    _self.$Message.info('上传成功');
                    _self.modal1 = false;
                    _self.getData();
                }
            });
        }
    }
});
