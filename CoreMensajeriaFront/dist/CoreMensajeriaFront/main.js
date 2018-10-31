(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["main"],{

/***/ "./src/$$_lazy_route_resource lazy recursive":
/*!**********************************************************!*\
  !*** ./src/$$_lazy_route_resource lazy namespace object ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncaught exception popping up in devtools
	return Promise.resolve().then(function() {
		var e = new Error("Cannot find module '" + req + "'");
		e.code = 'MODULE_NOT_FOUND';
		throw e;
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "./src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "./src/app/app.component.css":
/*!***********************************!*\
  !*** ./src/app/app.component.css ***!
  \***********************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ""

/***/ }),

/***/ "./src/app/app.component.html":
/*!************************************!*\
  !*** ./src/app/app.component.html ***!
  \************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ngx-app ng-version=\"6.0.0\">\r\n  <router-outlet></router-outlet>\r\n  <nb-auth _nghost-c53=\"\" class=\"ng-star-inserted\">\r\n    <nb-layout _ngcontent-c53=\"\" _nghost-c2=\"\">\r\n      <div _ngcontent-c2=\"\" class=\"scrollable-container\">\r\n        <div _ngcontent-c2=\"\" class=\"layout\">\r\n          <div _ngcontent-c2=\"\" class=\"layout-container\">\r\n            <div _ngcontent-c2=\"\" class=\"content\">\r\n              <div _ngcontent-c2=\"\" class=\"columns\">\r\n                <nb-layout-column _ngcontent-c53=\"\">\r\n                  <nb-card _ngcontent-c53=\"\" _nghost-c25=\"\">\r\n                    <nb-card-header _ngcontent-c53=\"\">\r\n                      <nav _ngcontent-c53=\"\" class=\"navigation\"><a _ngcontent-c53=\"\" aria-label=\"Back\" class=\"link\"\r\n                          href=\"#\"><i _ngcontent-c53=\"\" class=\"icon nb-arrow-thin-left\"></i></a></nav>\r\n                    </nb-card-header>\r\n                    <nb-card-body _ngcontent-c53=\"\">\r\n                      <nb-auth-block _ngcontent-c53=\"\" _nghost-c54=\"\">\r\n                        <router-outlet _ngcontent-c53=\"\"></router-outlet>\r\n                        <nb-login class=\"ng-star-inserted\">\r\n                          <h1 class=\"title\" id=\"title\">Login</h1>\r\n                          <p class=\"sub-title\">Hello! Log in with your email.</p>\r\n                          <!---->\r\n                          <!---->\r\n                          <form aria-labelledby=\"title\" novalidate=\"\" class=\"ng-touched ng-dirty ng-invalid\">\r\n                            <div class=\"form-control-group\"><label class=\"label\" for=\"input-email\">Email address:</label><input\r\n                                autofocus=\"\" fullwidth=\"\" id=\"input-email\" name=\"email\" nbinput=\"\" pattern=\".+@.+\\..+\"\r\n                                placeholder=\"Email address\" class=\"input-full-width input-md input-rectangle ng-untouched ng-pristine ng-invalid\"\r\n                                required=\"\">\r\n                              <!---->\r\n                            </div>\r\n                            <div class=\"form-control-group\"><label class=\"label\" for=\"input-password\">Password:</label><input\r\n                                fullwidth=\"\" id=\"input-password\" name=\"password\" nbinput=\"\" placeholder=\"Password\" type=\"password\"\r\n                                class=\"input-full-width input-md input-rectangle ng-untouched ng-pristine ng-invalid\"\r\n                                required=\"\" minlength=\"4\" maxlength=\"50\">\r\n                              <!---->\r\n                            </div>\r\n                            <div class=\"form-control-group accept-group\">\r\n                              <!---->\r\n                              <nb-checkbox name=\"rememberMe\" _nghost-c56=\"\" class=\"ng-valid ng-star-inserted ng-touched ng-dirty\"><label\r\n                                  _ngcontent-c56=\"\" class=\"customised-control\"><input _ngcontent-c56=\"\" class=\"customised-control-input\"\r\n                                    type=\"checkbox\"><span _ngcontent-c56=\"\" class=\"customised-control-indicator\"></span><span\r\n                                    _ngcontent-c56=\"\" class=\"customised-control-description\">Remember me</span></label></nb-checkbox><a\r\n                                class=\"forgot-password\" routerlink=\"../request-password\" href=\"#/auth/request-password\">Forgot\r\n                                Password?</a>\r\n                            </div><button fullwidth=\"\" nbbutton=\"\" status=\"success\" _nghost-c57=\"\" class=\"btn-success btn-full-width btn-disabled\"\r\n                              aria-disabled=\"true\" tabindex=\"-1\"> Log In </button>\r\n                          </form>\r\n                          <!---->\r\n                          <section aria-label=\"Social sign in\" class=\"links ng-star-inserted\"> or enter with: <div\r\n                              class=\"socials\">\r\n                              <!---->\r\n                              <!---->\r\n                              <!---->\r\n                              <!----><a href=\"https://github.com/akveo/nebular\" target=\"_blank\" class=\"socicon-github with-icon ng-star-inserted\"></a>\r\n                              <!---->\r\n                              <!---->\r\n                              <!---->\r\n                              <!----><a href=\"https://www.facebook.com/akveo/\" target=\"_blank\" class=\"socicon-facebook with-icon ng-star-inserted\"></a>\r\n                              <!---->\r\n                              <!---->\r\n                              <!---->\r\n                              <!----><a href=\"https://twitter.com/akveo_inc\" target=\"_blank\" class=\"socicon-twitter with-icon ng-star-inserted\"></a>\r\n                              <!---->\r\n                            </div>\r\n                          </section>\r\n                          <section aria-label=\"Register\" class=\"another-action\"> Don't have an account? <a class=\"text-link\"\r\n                              routerlink=\"../register\" href=\"#/auth/register\">Register</a></section>\r\n                        </nb-login>\r\n                      </nb-auth-block>\r\n                    </nb-card-body>\r\n                  </nb-card>\r\n                </nb-layout-column>\r\n              </div>\r\n            </div>\r\n          </div>\r\n        </div>\r\n      </div>\r\n    </nb-layout>\r\n  </nb-auth>\r\n</ngx-app>"

/***/ }),

/***/ "./src/app/app.component.ts":
/*!**********************************!*\
  !*** ./src/app/app.component.ts ***!
  \**********************************/
/*! exports provided: AppComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppComponent", function() { return AppComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = /** @class */ (function () {
    function AppComponent() {
        this.title = 'CoreMensajeriaFront';
    }
    AppComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-root',
            template: __webpack_require__(/*! ./app.component.html */ "./src/app/app.component.html"),
            styles: [__webpack_require__(/*! ./app.component.css */ "./src/app/app.component.css")]
        })
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "./src/app/app.module.ts":
/*!*******************************!*\
  !*** ./src/app/app.module.ts ***!
  \*******************************/
/*! exports provided: AppModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AppModule", function() { return AppModule; });
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _nebular_theme__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @nebular/theme */ "./node_modules/@nebular/theme/index.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _app_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./app.component */ "./src/app/app.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
 // we also need angular router for Nebular to function properly





var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_3__["NgModule"])({
            declarations: [
                _app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]
            ],
            imports: [
                _nebular_theme__WEBPACK_IMPORTED_MODULE_1__["NbThemeModule"].forRoot({ name: 'corporate' }),
                _angular_router__WEBPACK_IMPORTED_MODULE_0__["RouterModule"].forRoot([
                    { path: "", component: _app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"] }
                ]),
                _nebular_theme__WEBPACK_IMPORTED_MODULE_1__["NbLayoutModule"],
                _nebular_theme__WEBPACK_IMPORTED_MODULE_1__["NbSidebarModule"],
                _angular_platform_browser__WEBPACK_IMPORTED_MODULE_2__["BrowserModule"]
            ],
            providers: [_nebular_theme__WEBPACK_IMPORTED_MODULE_1__["NbSidebarService"]],
            bootstrap: [_app_component__WEBPACK_IMPORTED_MODULE_4__["AppComponent"]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "./src/environments/environment.ts":
/*!*****************************************!*\
  !*** ./src/environments/environment.ts ***!
  \*****************************************/
/*! exports provided: environment */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "environment", function() { return environment; });
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
var environment = {
    production: false
};
/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.


/***/ }),

/***/ "./src/main.ts":
/*!*********************!*\
  !*** ./src/main.ts ***!
  \*********************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/platform-browser-dynamic */ "./node_modules/@angular/platform-browser-dynamic/fesm5/platform-browser-dynamic.js");
/* harmony import */ var _app_app_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./app/app.module */ "./src/app/app.module.ts");
/* harmony import */ var _environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./environments/environment */ "./src/environments/environment.ts");




if (_environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].production) {
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["enableProdMode"])();
}
Object(_angular_platform_browser_dynamic__WEBPACK_IMPORTED_MODULE_1__["platformBrowserDynamic"])().bootstrapModule(_app_app_module__WEBPACK_IMPORTED_MODULE_2__["AppModule"])
    .catch(function (err) { return console.error(err); });


/***/ }),

/***/ 0:
/*!***************************!*\
  !*** multi ./src/main.ts ***!
  \***************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(/*! C:\Users\alexd\Documents\Alexander\UCAB\Materias\8. Oct-Ene 2018-2019\Desarrollo de Software\Proyecto\Core-de-Mensajeria\CoreMensajeriaFront\src\main.ts */"./src/main.ts");


/***/ })

},[[0,"runtime","vendor"]]]);
//# sourceMappingURL=main.js.map