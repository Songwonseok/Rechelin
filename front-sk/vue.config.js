module.exports = {
devServer: {
    port: 3000,
    proxy: 'https://maps.googleapis.com/maps/api/place/textsearch/json?input=국밥&inputtype=textquery&language=ko&fields=formatted_addres,name,geometry&key=AIzaSyDo6iueX6Cf9SPVlg9TdH8FIdfMgRshX9k/',
}
}