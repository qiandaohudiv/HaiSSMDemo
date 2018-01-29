/**
 * Created by Admin on 2017/9/15.
 */
new Vue({
    el: '#app',
    data: function () {
    	const validatePass = function(rule, value, callback){
            if (value === '') {
                callback(new Error('不能为空'));
            } else {
                callback();
            }
        };
        return {
            userType: '',
            openState: '',
            map:null,
            marker:null,
            new_point:null,
            columns: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center',
                    fixed: 'left'
                },
                {
                    title: '车牌号',
                    key: 'carNum',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '挂车牌号',
                    key: 'guacarNum',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '车牌颜色',
                    key: 'carnumColor',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '车辆类型',
                    key: 'carType',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '载重',
                    key: 'allowWeight',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '车辆归属',
                    key: 'ownType',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '经营许可证',
                    key: 'cerNum',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '驾驶员',
                    key: 'driverName',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '押运员',
                    key: 'safer',
                    align: 'center',
                    ellipsis: true
                },
                {
                    title: '位置',
                    key: 'action',
                    align: 'center',
                    render: function (h, params) {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                on: {
                                    click: function () {  
                                    	var _self=this;
                                    	_self.$Notice.destroy();
                                    	$.ajax({
                                    		dataType:'JSON',
                                    		type:'GET',
                                    		url:dataUrl.carInfo.map+encodeURIComponent(params.row.carNum),
                                            cache:false,
                                    		success:function(data){
                                    			if(data.longitude==null){
                                    				
                                            		_self.$Notice.info({
                                            			title:'无法获取该车辆的当前定位',
                                            			});
                                            	}
                                            	else{
                                            		/*_self.model2=true;
                                            		_self.map.clearOverlays();
                                                    _self.new_point = new BMap.Point(data.longitude,data.latitude);
                                                    _self.marker = new BMap.Marker(_self.new_point);  // 创建标注
                                                    _self.map.addOverlay(_self.marker);              // 将标注添加到地图中
                                                   setTimeout(function(){
                                                	   _self.map.panTo(_self.new_point);
                                                   },500);*/
                                            		_self.model2=true;
                                            		
                                            		_self.new_point=new BMap.Point(data.longitude,data.latitude);
                                                   setTimeout(function(){
                                                       var convertor = new BMap.Convertor();
                                                       var pointArr = [];
                                                       pointArr.push(_self.new_point);
                                                       convertor.translate(pointArr, 1, 5, translateCallback)
                                                   }, 500);
                                                   
                                                 //坐标转换完之后的回调函数
                                                   translateCallback = function (data){
                                                     if(data.status === 0) {
                                                       var marker = new BMap.Marker(data.points[0]);
                                                       _self.map.addOverlay(marker);
                                                       _self.map.setCenter(data.points[0]);
                                                     }
                                                   }
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
                                        	this.title = '修改货运信息';
                                            this.formValidate = JSON.parse(JSON.stringify(this.data1[params.index]));
                                            this.model1 = true;
                                            this.op = 1;
                                            //this.change(params.index);
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
                                            this.deleteOne(params.index);
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
                                    on: {
                                        click: function () {
                                            this.openState = "查看";
                                            this.data1[params.index].dp = 0;
                                            this.data1[params.index].sp = 0;
                                            this.driverList = [{name: this.data1[params.index].driverName}];
                                            this.saferList = [{name: this.data1[params.index].safer}];
                                            this.formValidate = JSON.parse(JSON.stringify(this.data1[params.index]));
                                            this.model1 = true;
                                        }.bind(this)
                                    }
                                }, '查看')
                            ]);
                        }
                    }.bind(this)
                }],
            data1: [],
            showModal: false,
            allChecked: '',
            theChecked: [],
            goodType: [],
            totalRecord: 0,
            content: {},
            page: {
                current: 1,
                pageNum: 20
            },
            op: 0,
            title: '',
            model1: false,
            model2:false,
            position: 0,
            formValidate: {
                carNum: '',
                carnumColor: '黄色',
                guacarNum: '',
                carColor: '',
                carType: '栏板货车',
                len: '',
                wide: '',
                high: '',
                allowWeight: '',
                weigh: '',
                totalWei: '',
                companyName: '',
                ownType: '',
                certNum: '',
                scope: '',
                insuranceDate: '',
                dutyInsurancedate: '',
                dutypeoDate: '',
                satellite: '',
                roadDate: '',
                certDate: '',
                certyearDate: '',
                certagrDate: '',
                yearcheckDate: '',
                volume: '',
                quality: '',
                tecDate: '',
                secImprovedate: '',
                driverName: '',
                safer: ''
            },
            ruleValidate: {
                carNum: [{required: true, message: '车牌号不能为空', trigger: 'blur'}],
                carnumColor: [{required: true, message: '车牌颜色不能为空', trigger: 'blur'}],
                allowWeight: [{required: true, validator: validatePass, trigger: 'blur' }],
                weigh: [{required: true, validator: validatePass, trigger: 'blur' }],
                totalWei: [{required: true, validator: validatePass, trigger: 'blur' }],
                companyName: [{required: true, message: '企业名称不能为空', trigger: 'blur'}],
                certNum: [{required: true, message: '道路运输号码不能为空', trigger: 'blur'}],
                scope: [{required: true, message: '经营范围不能为空', trigger: 'blur'}]
            },
            carType: ['栏板货车', '中性厢式货车', '重型箱式半挂车', '厢式货车', '罐式货车', '仓栅式货车', '重型半挂牵引车', '重型仓栅式半挂车', '重型罐式半挂车'],
            driverList: [],
            safeList: [],
            driver: {},
            safer: {},
            dp: 0, // 驾驶员
            sp: 0, // 安全员
            loading: false,
            searchText: ''
        }
    },
    created: function () {
        var _self = this;
        var textState = JSON.parse(Cookies.get("state"));
        if (textState != null) {
            if (textState.ID == 0) {
                window.location.href = "../../state.html";
            } else if (textState.ID == 1) {
                _self.formValidate.companyName = textState.companyName;
                if (textState.roleID == 1) {
                    _self.userType = 1;
                } else if (textState.roleID == 2) {
                    _self.userType = 2;
                } else {
                    _self.userType = 3;
                }
                _self.getAll();
            }
        } else {
            window.location.href = "../../state.html";
        }
    },
    mounted: function () {
        this.$refs.head.style.display = 'block';
        // 百度地图API功能
        this.map = new BMap.Map("allmap");
        this.map.centerAndZoom(new BMap.Point(116.331398,39.897445),16);
        this.map.enableScrollWheelZoom(true);
    },
    methods: {
    	
        search: function () {
            if (this.searchText.replace(/\s/g, '').length < 1) {
                alert('搜索内容不可为空');
            } else {
                var _self = this;
                $.ajax({
                    type: 'GET',
                    url: dataUrl.carInfo.search+encodeURI(_self.searchText.replace(/\s/g, '')),
                    cache: false,
                    success: function (data) {
                        if (typeof data == "object") {
                            _self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            _self.data1 = data.dataList;
                        } else {
                            _self.data1 = [];
                        }
                    }
                });
            }
        },
        theDriver: function () {
            this.driver = this.driverList[this.dp];
            this.formValidate.driverName = this.driverList[this.dp].name;
        },
        theSafer: function () {
            this.safer = this.safeList[this.sp];
            this.formValidate.safer = this.safeList[this.sp].name;
        },
        upCarMessage: function () { // 提交信息
            var _self = this;
            _self.loading = true;
            _self.$refs.formValidate.validate(function (valid) {
                if (_self.openState === '查看') {
                    _self.loading = false;
                    _self.model1 = false;
                } else {
                    if (valid) { // 验证通过
                        var theData = {};
                        for (var key in _self.formValidate) {
                            if (_self.formValidate[key] != '') {
                                theData[key] = _self.formValidate[key];
                            }
                        }
                        var url = '';
                        if (_self.op == 0) {
                            url = dataUrl.carInfo.insert;
                        } else {
                            url = dataUrl.carInfo.upDate;
                        }
                        $.ajax({
                            type: 'POST',
                            url: url,
                            data:theData,
                            cache: false,
                            success: function (data) {
                                _self.loading = false;
                                _self.model1 = false;
                                _self.getAll();
                                for (var key in _self.formValidate) {
                                    _self.formValidate[key] = '';
                                }
                                _self.formValidate.carnumColor = '黄色';
                                _self.formValidate.carType = '栏板货车';
                            },
                            error:function () {
                                _self.loading = false;
                            }
                        });
                    } else {
                        _self.loading = false;
                    }
                }

            });
        },
        cancel: function () {
            this.model1 = false;
            for (var key in this.formValidate) {
                this.formValidate[key] = '';
            }
        },
        showOne: function (index) {
            this.$Modal.info({
                title: '货物信息',
                content: '编号：' + this.data1[index].code + '<br>' +
                '名称：' + this.data1[index].name + '<br>' +
                '货物类型：' + this.data1[index].reserveTwo + '<br>' +
                '货物安全卡：' + this.data1[index].reserveThree + '<br>' +
                '应急方案：' + this.data1[index].reserveOne + '<br>' +
                '备注：' + this.data1[index].remark
            });
        },
        chooseAll: function (selection) { // 全选
            this.theChecked = [];
            for (var i = 0; i < selection.length; i++) {
                this.theChecked.push(selection[i].id);
            }
        },
        change: function (index) {
            this.title = '修改货运信息';
            this.formValidate = JSON.parse(JSON.stringify(this.data1[index]));
            this.model1 = true;
            this.op = 1;
        },
        time: function (times) {
            var d = new Date(parseInt(times));
            return d.getFullYear() + '/' + (d.getMonth() + 1) + '/' + d.getDate();
        },
        close: function (from) {
            this.model1 = false;
            if (from == 1) {
                this.getAll();
            }
        },
        open: function (obj) {
            this.model1 = true;
            if ($.trim(obj.currentTarget.innerText) == "添加") {
                this.$refs['formValidate'].resetFields();
                this.openState = $.trim(obj.currentTarget.innerText);
                if (this.formValidate.id != undefined)
                    this.formValidate.id = '';
            }
            ////请求最新驾驶员数据
            var _self = this;
            if(_self.userType===3){
                //根据公司ID查询数据
                _self.page.id=JSON.parse(Cookies.get("state")).companyID;
                $.ajax({
                    type: 'GET',
                    url: dataUrl.company.getPeople,
                    data:_self.page,
                    cache: false,
                    success: function (data) {
                        if (data.dataList != undefined) {
                            _self.driverList = [];
                            _self.safeList = [];
                            for (var i = 0; i < data.dataList.length; i++) {
                                if (data.dataList[i].peopleType == 1) {
                                    _self.driverList.push(data.dataList[i]);
                                } else if (data.dataList[i].peopleType == 2) {
                                    _self.safeList.push(data.dataList[i]);
                                }
                            }
                            // 给司机驾驶员赋初值
                            _self.driver = _self.driverList[_self.dp];
                            _self.safer = _self.safeList[_self.sp];

                            _self.formValidate.driverName = _self.driverList[_self.dp].name;
                            _self.formValidate.safer = _self.safeList[_self.sp].name;

                        }
                    }
                });
            }else{
                $.ajax({
                    type: 'GET',
                    url: dataUrl.person.all,
                    data:_self.page,
                    cache: false,
                    success: function (data) {
                        if (data.dataList != undefined) {
                            _self.driverList = [];
                            _self.safeList = [];
                            for (var i = 0; i < data.dataList.length; i++) {
                                if (data.dataList[i].peopleType == 1) {
                                    _self.driverList.push(data.dataList[i]);
                                } else if (data.dataList[i].peopleType == 2) {
                                    _self.safeList.push(data.dataList[i]);
                                }
                            }
                            // 给司机驾驶员赋初值
                            _self.driver = _self.driverList[_self.dp];
                            _self.safer = _self.safeList[_self.sp];
                            _self.formValidate.driverName = _self.driverList[_self.dp].name;
                            _self.formValidate.safer = _self.safeList[_self.sp].name;
                        }
                    }
                });
            }

        },
        deleteOne: function (index) {
            var arr = [this.data1[index].id];
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.carInfo.del + arr,
                cache: false,
                success: function (data) {
                    //_self.$Modal.success("删除成功!");
                    _self.getAll();
                }
            });
        },
        moreDelete: function () {
            var _self = this;
            if (this.theChecked.length > 0) {
                $.ajax({
                    type: 'GET',
                    url: dataUrl.carInfo.del + _self.theChecked,
                    cache: false,
                    success: function (data) {
                        alert('删除' + _self.theChecked.length + '条数据');
                        _self.getAll();
                    }
                });
            }
        },
        changePage: function (cur) {
            // 分页跳转
            this.page.current = cur;
            this.getAll();
        },
        getAll: function () {
            var _self = this;
            if (_self.userType === 3) {
                //根据公司Name查询数据
                _self.page.company_name=JSON.parse(Cookies.get("state")).companyName;
                $.ajax({
                    type: 'GET',
                    url: dataUrl.carInfo.getCarByCompanyName,
                    data:_self.page,
                    cache: false,
                    success: function (data) {
                        if (data != null) {
                            _self.data1 = data.dataList;
                            _self.totalRecord = data.totalRecord;
                            _self.current = data.currentPage;
                            _self.theChecked = [];
                        } else {
                            _self.data1 = [];
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
                        if (data != null) {
                            _self.data1 = data.dataList;
                            _self.totalRecord = data.totalRecord;
                            _self.current = data.currentPage;
                            _self.theChecked = [];
                        } else {
                            _self.data1 = [];
                        }
                    }
                });
            }
        }
    }
});
