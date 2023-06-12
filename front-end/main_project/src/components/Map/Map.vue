<template>
  <div>
    <div id="map"></div>
  </div>
</template>

<script>
import axios from "axios";
import * as echarts from "echarts";
import { MAPDATA } from "@/components/Map/mapData";
import { getMapData } from "@/api/dashboard";
export default {
  data() {
    return {
      alladcode: [],
    };
  },
  props: {},
  mounted() {
    let myChart = echarts.init(document.getElementById("map"));
    //各省份的地图json文件
    this.loadMap("100000", "Map", myChart); //初始化全国地图
    // let timeFn = null;
    //单击切换到省级地图，当mapCode有值,说明可以切换到下级地图
    // myChart.on('click', function (params) {
    //   clearTimeout(timeFn);
    //   //由于单击事件和双击事件冲突，故单击的响应事件延迟250毫秒执行
    //   timeFn = setTimeout(function () {
    //     let name = params.name; //地区name
    //     let clickRegionCode = this.alladcode.filter(areaJson => {
    //       return areaJson.properties.name === params.name
    //     })[0].properties.adcode;
    //     this.loadMap(clickRegionCode, name, myChart).then((ret) => {
    //       if (ret == true) {
    //         preCode.push([clickRegionCode, params.name])
    //       }
    //     })
    //
    //     this.$emit('change', name.replaceAll(/[省|市|自治区|壮族自治区|回族自治区|维吾尔族自治区]/g, ""))
    //     return
    //   }, 250);
    // });

    // 绑定双击事件，返回上级
    // myChart.on('dblclick', function (params) {
    //   //当双击事件发生时，清除单击事件，仅响应双击事件
    //   clearTimeout(timeFn);
    //   if (preCode.length != 1) {
    //     preCode.pop()
    //     let codeAdd = preCode[preCode.length - 1]
    //     this.loadMap(codeAdd[0], codeAdd[1], myChart);
    //     this.$emit('change', codeAdd[1].replaceAll(/[省|市|自治区|壮族自治区|回族自治区|维吾尔族自治区]/g, ""))
    //   } else {
    //     return
    //   }
    // });
  },
  methods: {
    loadMap(mapCode, name, myChart) {
      myChart.showLoading();
      echarts.registerMap("Map", MAPDATA);
      let option = {
        layoutCenter: ["50%", "70%"],
        layoutSize: 280, //位置
        title: {
          text: name,
          left: "0",
          textStyle: {
            color: "black",
            fontSize: 15,
            fontFamily: "微软雅黑",
          },
        },
        tooltip: {
          show: true,
          formatter: function (params) {
            if (params.data) return params.name + ":" + params.data["value"];
          },
        },
        visualMap: {
          type: "continuous",
          text: ["", "样本量"],
          showLabel: true,
          left: "10",
          top: "250",
          min: 0,
          // max: max,
          inRange: {
            color: ["#FFEC8B", "#98FB98"],
          },
          splitNumber: 0,
          bottom: 100,
          textStyle: {
            color: "#7986ff",
          },
        },
        series: [
          {
            name: "MAP",
            type: "map",
            textStyle: {
              fontFamily: "微软雅黑",
              fontSize: 12,
              fontStyle: "normal",
              fontWeight: "normal",
              color: "#fff",
            },
            map: name,
            selectedMode: false, //是否允许选中多个区域
            label: {
              normal: {
                show: true,
              },
              emphasis: {
                show: true,
              },
            },
            zoom: 2, //当前视角的缩放比例
            roam: true, //是否开启平游或缩放
            scaleLimit: {
              //滚轮缩放的极限控制
              min: 2,
              max: 80,
            },
            data: [{ name: "上海市", value: 251 }],
          },
        ],
      };
      option && myChart.setOption(option);
      getMapData().then((res) => {
        const { data } = res;
        myChart.setOption({
          series: [
            {
              data: data,
            },
          ],
        });
        myChart.hideLoading();
      });
      // return new Promise((resolve) => {
      //
      //     $(".container").show()
      //     $("#loadAdd").text("正在获取 " + name + " 地图数据")
      //     axios.get('http://localhost:8086/getMap?code=' + mapCode).then(function ({data}) {
      //       this.alladcode = data.features
      //       let allData = []
      //       console.error(data)
      //       // 地图呈现数据
      //       if (data) {
      //         let proc = []
      //         for (let i = 0; i < data.features.length; i++) {
      //           let tName = data.features[i].properties.name
      //           if (tName != "") {
      //             proc.push(new Promise((resolve) => {
      //               get({
      //                 url: '/gettotalorder',
      //                 params: {
      //                   address: tName
      //                 }
      //               }).then(data => {
      //                 allData.push({
      //                   name: tName,
      //                   value: data[tName] == null ? 0 : data[tName]
      //                 })
      //                 resolve(allData)
      //               })
      //               // $("#loadAdd").text('加载' + tName + "中..")
      //             }))
      //           }
      //         }
      //         Promise.all(proc).then((res) => {
      //           // $(".container").hide()
      //           let Data = res[0]
      //           let max = 0
      //           echarts.registerMap(name, data);
      //           console.error(Data)
      //           for (let data in Data) {
      //             max = Math.max(max, Data[data].value)
      //           }
      //
      //           let option = {
      //             layoutCenter: ['50%', '70%'],
      //             layoutSize: 280, //位置
      //             title: {
      //               text: name,
      //               left: '0',
      //               textStyle: {
      //                 color: "black",
      //                 fontSize: 15,
      //                 fontFamily: '微软雅黑'
      //               }
      //             },
      //             tooltip: {
      //               show: true,
      //               formatter: function (params) {
      //                 if (params.data) return params.name + ':' +
      //                   params.data['value']
      //               },
      //             },
      //             visualMap: {
      //               type: 'continuous',
      //               text: ['', '样本量'],
      //               showLabel: true,
      //               left: '10',
      //               top: '250',
      //               min: 0,
      //               max: max,
      //               inRange: {
      //                 color: ['#FFEC8B', '#98FB98'
      //                 ]
      //               },
      //               splitNumber: 0,
      //               bottom: 100,
      //               textStyle: {
      //                 color: "#7986ff"
      //               }
      //             },
      //             series: [{
      //               name: 'MAP',
      //               type: 'map',
      //               textStyle: {
      //                 fontFamily: '微软雅黑',
      //                 fontSize: 12,
      //                 fontStyle: 'normal',
      //                 fontWeight: 'normal',
      //                 color: '#fff'
      //
      //               },
      //               map: name,
      //               selectedMode: false, //是否允许选中多个区域
      //               label: {
      //                 normal: {
      //                   show: true
      //                 },
      //                 emphasis: {
      //                   show: true
      //                 }
      //               },
      //               zoom: 2, //当前视角的缩放比例
      //               roam: true, //是否开启平游或缩放
      //               scaleLimit: { //滚轮缩放的极限控制
      //                 min: 2,
      //                 max: 80
      //               },
      //               data: Data
      //             }],
      //           };
      //           myChart.setOption(option, true); //重新绘制一下初始框架
      //         })
      //         resolve(true)
      //       }
      //     }).catch((err) => {
      //       console.log(err)
      //       resolve(false)
      //     })
      //
      // })
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 900px;
}
</style>
