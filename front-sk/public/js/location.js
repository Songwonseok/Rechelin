
function haversine_distance(mk1_lat, mk1_lng, mk2_lat, mk2_lng) {
    //var R = 3958.8; // Radius of the Earth in miles
    var R = 6371.0710 // in kilometer
    //var rlat1 = mk1.position.lat() * (Math.PI / 180); // Convert degrees to radians
    //var rlat2 = mk2.position.lat() * (Math.PI / 180); // Convert degrees to radians
    var rlat1 = mk1_lat * (Math.PI / 180)
    var rlat2 = mk2_lat * (Math.PI / 180)
    var difflat = rlat2 - rlat1; // Radian difference (latitudes)
    //var difflon = (mk2.position.lng() - mk1.position.lng()) * (Math.PI / 180); // Radian difference (longitudes)
    var difflon = (mk2_lng - mk1_lng) * (Math.PI / 180); // Radian difference (longitudes)
    var d = 2 * R * Math.asin(Math.sqrt(Math.sin(difflat / 2) * Math.sin(difflat / 2) + Math.cos(rlat1) * Math.cos(rlat2) * Math.sin(difflon / 2) * Math.sin(difflon / 2)));
    return d;
}

export { haversine_distance}