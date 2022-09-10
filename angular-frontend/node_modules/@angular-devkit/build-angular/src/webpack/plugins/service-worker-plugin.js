"use strict";
/**
 * @license
 * Copyright Google LLC All Rights Reserved.
 *
 * Use of this source code is governed by an MIT-style license that can be
 * found in the LICENSE file at https://angular.io/license
 */
Object.defineProperty(exports, "__esModule", { value: true });
exports.ServiceWorkerPlugin = void 0;
const service_worker_1 = require("../../utils/service-worker");
class ServiceWorkerPlugin {
    constructor(options) {
        this.options = options;
    }
    apply(compiler) {
        compiler.hooks.done.tapPromise('angular-service-worker', async (_compilation) => {
            const { projectRoot, root, baseHref = '', ngswConfigPath, outputPath } = this.options;
            await (0, service_worker_1.augmentAppWithServiceWorker)(projectRoot, root, outputPath, baseHref, ngswConfigPath, 
            // eslint-disable-next-line @typescript-eslint/no-explicit-any
            compiler.inputFileSystem.promises, 
            // eslint-disable-next-line @typescript-eslint/no-explicit-any
            compiler.outputFileSystem.promises);
        });
    }
}
exports.ServiceWorkerPlugin = ServiceWorkerPlugin;
