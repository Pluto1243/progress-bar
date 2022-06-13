// 提交新增
function submitProgress() {
    const name = $('#name').val()
    const unit = $('#unit').val()
    const current = $('#current').val()
    const total = $('#total').val()
    const userId = $('#userId').val()
    const fileId = $('#uploadFileId').val()
    const json = {
        'name': name,
        'unit': unit,
        'current': current,
        'total': total,
        'userId': userId,
        'fileId': fileId
    }
    $.ajax({
        url: url + '/progress/insertProgress',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(json),
        async: false,
        success: function (result) {
            if (result != null && result.code == 0) {
                alert('提交成功')
                initOption(1, myChart1);
                initOption(2, myChart2);
            } else if (result != null) {
                alert(result.data.errMsg)
            } else {
                alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
            }
        },
        errors: function () {
            alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
        }
    })

    closeProgress()
}

//上传文件
function UploadFile(files) {
    console.log('ready for upload', files[0]);
    const formData = new FormData();
    formData.append('file', files[0]);
    $.ajax({
        url: url + '/file/uploadFile',
        type: 'POST',
        processData: false,
        cache: false,
        data: formData ,
        dataType: 'json',
        contentType: false,
        async: false,
        success: function (result) {
            if (result != null && result.code == 0) {
                alert('上传成功')
                console.log(result);
                $('#uploadFileId').val(result.data.id.toString());
                const path = result.data.path;
                console.log(path);
                $('#pic').prop('src', path);
                $('#pic').prop('style', 'width: 60px; margin-bottom: 8px');
            } else if (result != null) {
                alert(result.data.errMsg)
            } else {
                alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
            }
        },
        errors: function () {
            alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
        }
    })
}

// 提交修改
function updateProgress() {
    const id = $('#id').val()
    const current = $('#current1').val()
    const total = $('#total1').val()
    const userId = $('#userId1').val()
    const json = {
        'id': id,
        'current': current,
        'total': total,
        'userId': userId
    }
    $.ajax({
        url: url + '/progress/updateProgress',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(json),
        async: false,
        success: function (result) {
            if (result != null && result.code == 0) {
                alert('打卡成功！')
                initOption(1, myChart1);
                initOption(2, myChart2);
            } else if (result != null) {
                alert(result.data.errMsg)
            } else {
                alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
            }
        },
        errors: function () {
            alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
        }
    })

    closeProgress()
}

function deleteProgress() {
    const res = confirm("你真的要删除吗？😢")
    if (res) {
        const id = $('#id').val()
        const json = {
            'id': id
        }
        $.ajax({
            url: url + '/progress/deleteProgress',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(json),
            async: false,
            success: function (result) {
                if (result != null && result.code == 0) {
                    $('#myForm1').modal('hide');
                    alert('啊！我被删掉了😖')
                    initOption(1, myChart1);
                    initOption(2, myChart2);
                } else if (result != null) {
                    alert(result.data.errMsg)
                } else {
                    alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
                }
            },
            errors: function () {
                alert('啊哦，出问题了ε＝ε＝ε＝(#>д<)ﾉ')
            }
        })

    }
}

function getProgressById(id) {
    $.ajax({
        url: url + '/progress/getProgressById?progressId=' + id,
        type: 'GET',
        dataType: 'json',
        async: false,
        success: function (result) {
            $('#name1').val(result.data.name)
            $('#userId1').val(result.data.userId)
            $('#id').val(result.data.id)
            $('#current1').val(result.data.current)
            $('#total1').val(result.data.total)
            $('#unit1').val(result.data.unit)
        }
    })
}


// 清空表单
function closeProgress() {
    $('#progressForm input').each(function (){
        $(this).val('')
    })
    $('#pic').prop("src", '');
    $('#pic').prop("style", 'display: none')
    document.querySelector('#myForm').reset()
}

// 动态修改表单
function changeForm(value) {
    $('#current').attr('placeholder', '当前进度：'+value)
    $('#currentLable').html('当前进度：'+value)
    $('#total').attr('placeholder', '目标进度：'+value)
    $('#totalLabel').html('目标进度：'+value)
}

function changeUserLabel() {
    $('#userLabel').html('')
}

function initCheckCharts() {
    const year = new Date().getFullYear()
    const userId = $('#checkUserId').val()
    const yearDatas = getYearData(year, userId)
    const myChart = echarts.init(document.getElementById('checkChartsBody'), null, {
        renderer: 'canvas',
        useDirtyRect: true
    });
    var option;

    option = {
        visualMap: {
            show: false,
            inRange: {
                color: ['#FFFFFF', '#9BE9A8', '#40C463', '#30A84E', '#216E39'],
            },
            min: 0,
            max: 10
        },
        tooltip: {
            formatter: function (params) {
                return "<span style='font-family: myFont'>"+ params.data[0] + "</span><br/>" +
                    "<span style='font-family: myFont'>打卡了 </span>" +
                    "<span style='font-family: myFont; font-weight: bold'>"+ params.value[1] + "</span>" +
                    "<span style='font-family: myFont'> 次</span>";
            },
        },
        calendar: {
            range: year,
            splitLine: {
                lineStyle: {
                    width: 1.5,
                    color: "#fff",
                },
            },
            monthLabel: {
                fontFamily: 'myFont'
            },
            yearLabel: {
                fontFamily: 'myFont'
            },
            dayLabel: {
                fontFamily: 'myFont'
            }

        },
        series: {
            type: 'heatmap',
            coordinateSystem: 'calendar',
            calendarIndex: 0,
            data: yearDatas
        }
    };

    if (option && typeof option === 'object') {
        myChart.setOption(option);
    }
    window.addEventListener('resize', myChart.resize);
}

// 获得一年以来的打卡记录
function getYearData(year, userId) {
    var date = +echarts.number.parseDate(year + '-01-01');
    var end = +echarts.number.parseDate(year + '-12-31');
    var dayTime = 3600 * 24 * 1000;
    var data = [];
    // 根据ID获取到提交记录
    $.ajax({
        url: url + '/check/listCheck?userId=' + userId,
        type: 'GET',
        dataType: 'json',
        async: false,
        success: function (result) {
            if (result.data != null) {
                result.data.forEach(function (item) {
                    data[item.date] = item.count
                })
            }
        }
    })
    for (var time = date; time <= end; time += dayTime) {
        var formatTime = echarts.format.formatTime('yyyy-MM-dd', time)
        if (data.hasOwnProperty(formatTime)) {
            data.push([
                formatTime,
                data[formatTime]
            ])
        } else {
            data.push([
                formatTime,
                0
            ]);
        }
    }
    return data;
}