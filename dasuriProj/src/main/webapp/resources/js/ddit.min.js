function openHomeSearch() {
	new daum.Postcode({
		oncomplete: function(data) {
			$('[name=zipcode]').val(data.zonecode); // 우편번호 (5자리)
			$('[name=addr1]').val(data.address);
			$('[name=addr2]').val(data.buildingName);
		}
	}).open();
}