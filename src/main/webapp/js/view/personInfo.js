new Vue({
    el: '#app',
    data:function() {
        return {
            userType: '',
            uploadUrl:dataUrl.person.upImg,
            companyList: [],
            totalRecord: 0,
            page: {
                current: 1, /*当前页*/
                pageNum: 20/*每页数据量*/,
            },
            isDriver: false, // 判断是否为司机
            formValidate: {
                id: '',
                name: '',
                sex: '',
                birthday: '',
                phone: '',
                compayId: '',
                peopleStatus: '',
                peopleType: '',
                driveLicence: '',
                driLicenceTime: '',
                drivezigezheng: '',
                driverzigezhengTime: '',
                driverType: '',
                pictureName: '',
                pictureUrl: '',
                adresss: '',
                picUser: ''
            },
            ruleValidate: {
                name: [
                    {required: true, message: '姓名不能为空', trigger: 'blur'}
                ],
                sex: [
                    {required: true, message: '请选择性别', trigger: 'change'}
                ],
                phone: [
                    {required: true, message: '手机号不能为空', trigger: 'blur'}
                ],
                compayId: [
                    {required: true, message: '请选择单位', trigger: 'change', type: 'number'}
                ],
                peopleStatus: [
                    {required: true, message: '请选择状态', trigger: 'change'}
                ],
                peopleType: [
                    {required: true, message: '请选择类型', trigger: 'change'}
                ],
                drivezigezheng: [
                    {required: true, message: '从业资格证不能为空', trigger: 'blur'}
                ],
                driverType: [
                    {required: true, message: '请选择类型', trigger: 'change'}
                ],
                adresss: [
                    {required: true, message: '地区不能为空', trigger: 'blur'}
                ]
            },
            modal1: false,
            columns: [
                {
                    type: 'index',
                    width: 60,
                    align: 'center',
                    fixed: 'left'
                },
                {
                    type: 'selection',
                    width: 60,
                    align: 'center',
                    fixed: 'left'
                },
                {
                    title: '姓名',
                    key: 'name',
                    align: 'center',
                    width: 100
                },
                {
                    title: '性别',
                    key: 'sex',
                    align: 'center',
                    width: 100
                },
                {
                    title: '出生日期',
                    key: 'birthday',
                    align: 'center',
                    sortable: true,
                    width: 120
                },
                {
                    title: '所在单位',
                    align: 'center',
                    key: 'reserveOne',
                    width: 200
                },
                {
                    title: '联系电话',
                    key: 'phone',
                    align: 'center',
                    width: 120
                },
                {
                    title: '类别',
                    key: 'type',
                    align: 'center',
                    width: 100
                },
                {
                    title: '驾驶证',
                    key: 'driveLicence',
                    align: 'center',
                    width: 100
                },
                {
                    title: '有效期',
                    key: 'driLicenceTime',
                    sortable: true,
                    align: 'center',
                    width: 100
                },
                {
                    title: '从业资格证',
                    key: 'drivezigezheng',
                    align: 'center',
                    width: 120
                },
                {
                    title: '有效期',
                    key: 'driverzigezhengTime',
                    sortable: true,
                    align: 'center',
                    width: 100
                },
                {
                    title: '地址',
                    align: 'center',
                    key: 'adresss',
                    width: 200
                },
                {
                    title: '操作',
                    key: 'action',
                    width: 150,
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
                                    on: {
                                        click: function () {
                                            this.openState = "查看";
                                            this.formValidate = JSON.parse(JSON.stringify(this.data[params.index]));
                                            this.modal1 = true;
                                        }.bind(this)
                                    }
                                }, '查看')
                            ]);
                        }

                    }.bind(this)
                }
            ],
            data: [],
            openState: '',
            loading: false,
            delArr: [],
            searchText: ''
        }
    },
    methods: {
        search:function() {
            var _self = this;
            if (_self.searchText.replace(/\s/g, '').length < 1) {
                alert('搜索内容不可为空');
            } else {
                $.ajax({
                    type: 'GET',
                    url: dataUrl.person.search + encodeURI(this.searchText.replace(/\s/g, '')),
                    cache: false,
                    success: function (data) {
                        if (typeof data == "object") {
                        	_self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            for (var i in data.dataList) {
                                if (data.dataList[i].peopleType == 1) {
                                    data.dataList[i].type = '驾驶员';
                                } else if (data.dataList[i].peopleType == 2) {
                                    data.dataList[i].type = '押运员';
                                }
                                data.dataList[i].picUser = baseUrl + '/pic/' + data.dataList[i].pictureName;
                                data.dataList[i].birthday = _self.format(data.dataList[i].birthday);
                                data.dataList[i].driLicenceTime = _self.format(data.dataList[i].driLicenceTime);
                                data.dataList[i].driverzigezhengTime = _self.format(data.dataList[i].driverzigezhengTime);
                            }
                            _self.data = data.dataList;
                        } else {
                            _self.data = [];
                        }
                    }
                });
            }
        },
        // 公司名称
        companyChange:function(company) {
            this.formValidate.reserveOne = company.label;
        },
        changeType:function(index) {
            if (index == 1) {
                this.isDriver = true;
            } else {
                this.isDriver = false;
                this.formValidate.driveLicence = '';
                this.formValidate.driveLicenceTime = '';
            }
        },
        del:function() {
            var _self = this;
            $.ajax({
                type: 'GET',
                url: dataUrl.person.del + _self.delArr,
                cache: false,
                success: function (data) {
                    _self.delArr = [];
                    _self.getData();
                    _self.$Message.info('刪除成功');
                }
            });
        },
        chooseAll:function(data) {
            var _self = this;
            for (var i in data) {
                _self.delArr.push(data[i].id);
            }
        },
        success:function(data) {
            this.formValidate.pictureName = data[0];
            this.formValidate.pictureUrl = data[1];
            this.loadingStatus = false;
            this.formValidate.picUser = baseUrl + '/pic/' + this.formValidate.pictureName;
            this.$Message.success('上传成功');
        },
        error:function() {
            this.$Message.success('上传失败');
        },
        handleFormatError:function(file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize:function(file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        next:function(data) {
            this.page.current = data;
            this.getData();
        },
        add0:function(m) {
            return m < 10 ? '0' + m : m
        },
        format:function(nS) {
            var time = new Date(nS);
            var y = time.getFullYear();
            var m = time.getMonth() + 1;
            var d = time.getDate();
            return y + '-' + this.add0(m) + '-' + this.add0(d);
        },
        open:function(obj) {
            if ($.trim(obj.currentTarget.innerText) == "添加") {
                this.$refs['formValidate'].resetFields();
                this.formValidate.picUser = null;
                this.openState = $.trim(obj.currentTarget.innerText);
                if (this.formValidate.id != undefined)
                    this.formValidate.id = '';
            }
            this.modal1 = true;
        },
        getData:function() {
            var _self = this;
            _self.$Loading.start();
            if (_self.userType === 3) {
                //根据公司ID查询数据
                _self.page.id=JSON.parse(Cookies.get("state")).companyID;
                $.ajax({
                    type: 'GET',
                    url: dataUrl.company.getPeople,
                    data:_self.page,
                    cache: false,
                    success: function (data) {
                        _self.$Loading.finish();

                        if (typeof data == "object") {
                            _self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            for (var i in data.dataList) {
                                if (data.dataList[i].peopleType == 1) {
                                    data.dataList[i].type = '驾驶员';
                                } else if (data.dataList[i].peopleType == 2) {
                                    data.dataList[i].type = '押运员';
                                }
                                data.dataList[i].picUser = baseUrl + '/pic/' + data.dataList[i].pictureName;
                                data.dataList[i].birthday = _self.format(data.dataList[i].birthday);
                                data.dataList[i].driLicenceTime = _self.format(data.dataList[i].driLicenceTime);
                                data.dataList[i].driverzigezhengTime = _self.format(data.dataList[i].driverzigezhengTime);
                            }
                            _self.data = data.dataList;
                        } else {
                            _self.data = [];
                        }
                    }
                });
            } else {

                $.ajax({
                    type:'GET',
                    url:dataUrl.person.all,
                    cache:false,
                    data: _self.page,
                    success: function(data){
                        _self.$Loading.finish();
                        if (typeof data == "object") {
                            _self.totalRecord = data.totalRecord;
                            _self.page.current = data.currentPage;
                            for (var i in data.dataList) {

                                if (data.dataList[i].peopleType == 1) {
                                    data.dataList[i].type = '驾驶员';
                                } else if (data.dataList[i].peopleType == 2) {
                                    data.dataList[i].type = '押运员';
                                }
                                data.dataList[i].picUser = baseUrl + '/pic/' + data.dataList[i].pictureName;
                                data.dataList[i].birthday = _self.format(data.dataList[i].birthday);
                                data.dataList[i].driLicenceTime = _self.format(data.dataList[i].driLicenceTime);
                                data.dataList[i].driverzigezhengTime = _self.format(data.dataList[i].driverzigezhengTime);
                            }
                            _self.data = data.dataList;
                        } else {
                            _self.data = [];
                        }
                    }
                })
            }
        },
        upMessage:function() {
            var _self = this;
            _self.loading = true;
            _self.$refs.formValidate.validate(function (valid) {
                if (_self.openState == "查看") {
                    _self.loading = false;
                    _self.modal1 = false;
                } else {
                    if (valid) {
                        //delete  _self.formValidate.picUser;
                        _self.formValidate.birthday = _self.format(_self.formValidate.birthday);
                        _self.formValidate.driLicenceTime = _self.format(_self.formValidate.driLicenceTime);
                        _self.formValidate.driverzigezhengTime = _self.format(_self.formValidate.driverzigezhengTime);
                        if (_self.openState == "添加") {
                            _self.postData(_self, dataUrl.person.insert,_self.formValidate);
                        }
                        else if (_self.openState == "修改") {
                            _self.postData(_self, dataUrl.person.upDate,_self.formValidate);
                        }
                    } else {
                        _self.loading = false;
                    }
                }
            });
        },
        cancel:function() {
            this.modal1 = false;
            this.loading = false;
        },
        postData:function(_self, url, data) {
            $.ajax({
                type: 'POST',
                url: url,
                data:data,
                cache: false,
                success: function (data) {
                    _self.loading = false;
                    _self.modal1 = false;
                    _self.$Message.info('上传成功');
                    _self.getData();
                },
                error:function () {
                    _self.$Message.info('上传失败');
                }
            });
        }
    },
    created:function() {
        var _self = this;
        var textState = JSON.parse(Cookies.get("state"));
        if (textState != null) {
            if (textState.ID == 0) {
                window.location.href = "../../state.html";
            } else if (textState.ID == 1) {
                if (textState.roleID == 1) {
                    _self.userType = 1;
                    $.ajax({
                        type: 'GET',
                        url: dataUrl.person.companyList,
                        cache: false,
                        success: function (data) {
                            for (var i in data) {
                                _self.companyList.push({id: data[i].id, name: data[i].name});
                            }
                        }
                    });
                } else if (textState.roleID == 2) {
                    _self.userType = 2;
                    $.ajax({
                        type: 'GET',
                        url: dataUrl.person.companyList,
                        cache: false,
                        success: function (data) {
                            for (var i in data) {
                                _self.companyList.push({id: data[i].id, name: data[i].name});
                            }
                        }
                    });
                } else {
                    _self.userType = 3;
                    $.ajax({
                        type: 'GET',
                        url: dataUrl.company.getName + textState.companyID,
                        cache: false,
                        success: function (data) {
                            _self.companyList.push({id: data.id, name: data.name});
                        }
                    });
                }
                _self.getData();
            }
        } else {
            window.location.href = "../../state.html";
        }
    },
    mounted: function () {
        this.$refs.head.style.display = 'block';
    }
});