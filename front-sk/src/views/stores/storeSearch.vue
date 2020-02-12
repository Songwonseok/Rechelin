<template>
  <div>
      <treeselect
            v-model="value"
            placeholder="hashtag 를 선택해주세요..."
            :multiple="true"
            :options="options"/>
  </div>
</template>

<script>
 import Treeselect from '@riophae/vue-treeselect'
 import '@riophae/vue-treeselect/dist/vue-treeselect.css'
export default {
     components: {
            Treeselect
        },
        watch : {
            value : function(v){
                console.log(v);
            }
        },
        data() {
            return {
                value: [],
                options: [
                    {
                        id: 'area',
                        label: '강남',
                        children: [
                            {
                                id: 'calm',
                                label: '조용한 😴',
                                isNew: true
                            }, {
                                id: 'warm',
                                label: '따뜻한 😍'
                            }, {
                                id: 'family',
                                label: '가족과 😣'
                            }, {
                                id: 'couple',
                                label: '연인과 😌'
                            }, {
                                id: 'cheap',
                                label: '값싼 😚'
                            }
                        ]
                    }, {
                        id: 'atmosphere',
                        label: '분위기',
                        children: [
                            {
                                id: 'loud',
                                label: '시끌벅적 😐'
                            }, {
                                id: 'many',
                                label: '매우 많은 🤐'
                            }, {
                                id: 'cold',
                                label: '차가운 😲'
                            }, {
                                id: 'kind',
                                label: '친절한 😖'
                            }
                        ]
                    }
                ]
            }
        }
}
</script>

<style>

</style>