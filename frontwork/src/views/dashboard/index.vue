<template>
  <div class="container">
    <div ref="area" class="bar"></div>
    <div ref="time" class="line"></div>
    <Map style="width: 100%;"></Map>
  </div>

</template>

<script>
import {mapGetters} from 'vuex'
import Map from "@/components/Map/Map.vue";

export default {
  name: 'Dashboard',
  components:{
    Map
  },
  data() {
    return {
      list: [],
    }
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  mounted() {
    this.initBar()
    this.initLine()
  },
  methods: {
    initBar() {
      let option = {
        title: {
          text: '植物种类地区分布统计'
        },
        backgroundColor: '',
        tooltip: {},
        legend: {
          left: '60%'
        },
        grid: {

        },
        xAxis: {
          type: 'category',
          data: ['番茄', '苹果', '葡萄'],
        },
        yAxis: {
          type: 'value',
        },
        series: [
          {
            name: '北京',
            type: 'bar',
            data: [1089, 544, 200]
          },
          {
            name: '上海',
            type: 'bar',
            data: [229, 121, 111]
          },
          {
            name: '广州',
            type: 'bar',
            data: [551, 152, 666]
          }
        ]
      }
      let bar = this.$echarts.init(this.$refs.area)
      bar.setOption(option)
    },
    initLine() {
      let base = +new Date(2000, 9, 3);
      let oneDay = 24 * 3600 * 1000;
      let data = [[base, Math.random() * 300]];
      for (let i = 1; i < 20000; i++) {
        let now = new Date((base += oneDay));
        data.push([+now, Math.round((Math.random() - 0.5) * 20 + data[i - 1][1])]);
      }
      let option = {
        tooltip: {
          trigger: 'axis',
          position: function (pt) {
            return [pt[0], '10%'];
          }
        },
        title: {
          left: 'center',
          text: '植物病害出现时间统计'
        },
        toolbox: {
          feature: {
            dataZoom: {
              yAxisIndex: 'none'
            },
            restore: {},
            saveAsImage: {}
          }
        },
        xAxis: {
          type: 'time',
          boundaryGap: false
        },
        yAxis: {
          type: 'value',
          boundaryGap: [0, '100%']
        },
        dataZoom: [
          {
            type: 'inside',
            start: 0,
            end: 20
          },
          {
            start: 0,
            end: 20
          }
        ],
        series: [
          {
            name: 'Fake Data',
            type: 'line',
            smooth: true,
            symbol: 'none',
            areaStyle: {},
            data: data
          }
        ]
      };
      let line = this.$echarts.init(this.$refs.time)
      line.setOption(option)
    }
  },

}
</script>

<style lang="scss" scoped>
.container {
  width: 90%;
  min-height: calc(100vh - 50px);
  margin: 10vh auto;
  display: flex;
  flex-wrap: wrap;
  .bar {
    width: 50%;
    height: 600px;
  }

  .line {
    width: 50%;
    height: 600px;
  }
}

</style>
